<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Biblioteca Online UNICESUMAR</title>

  <!-- Ícones da Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

  <!-- Estilo com suporte a tema claro e escuro -->
  <style>
    :root {
      --bg: #f0f0f0;
      --bg-card: #ffffff;
      --text: #222;
      --accent: #007bff;
      --input-bg: #ffffff;
      --input-border: #ccc;
      --btn-bg: linear-gradient(90deg, #00c6ff, #0072ff);
      --btn-hover: linear-gradient(90deg, #0072ff, #00c6ff);
      --shadow: rgba(0,0,0,0.1);
    }

    body.dark {
      --bg: #121212;
      --bg-card: #1e1e1e;
      --text: #e0e0e0;
      --accent: #00bcd4;
      --input-bg: #2c2c2c;
      --input-border: #444;
      --btn-bg: linear-gradient(90deg, #00c6ff, #0072ff);
      --btn-hover: linear-gradient(90deg, #0072ff, #00c6ff);
      --shadow: rgba(0,0,0,0.4);
    }

    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: var(--bg);
      color: var(--text);
      margin: 0;
      padding: 30px 15px;
      transition: background 0.4s ease, color 0.4s ease;
    }

    .theme-toggle {
      position: absolute;
      top: 20px;
      right: 30px;
      cursor: pointer;
      font-size: 1.2rem;
      background: none;
      border: none;
      color: var(--text);
      transition: color 0.3s ease;
    }

    h2 {
      color: var(--accent);
      text-align: center;
      margin-bottom: 25px;
    }

    .container-form {
      max-width: 500px;
      background: var(--bg-card);
      padding: 30px 35px;
      margin: 0 auto;
      border-radius: 12px;
      box-shadow: 0 10px 20px var(--shadow);
      animation: fadeIn 0.6s ease;
    }

    form div {
      margin-bottom: 18px;
      position: relative;
    }

    label {
      display: block;
      margin-bottom: 6px;
      font-weight: bold;
    }

    input[type="text"],
    input[type="number"] {
      width: 100%;
      padding: 12px 15px 12px 40px;
      background-color: var(--input-bg);
      border: 2px solid var(--input-border);
      border-radius: 8px;
      font-size: 1rem;
      color: var(--text);
      transition: 0.3s;
    }

    input:focus {
      border-color: var(--accent);
      box-shadow: 0 0 6px var(--accent);
      outline: none;
    }

    .input-icon {
      position: absolute;
      left: 12px;
      top: 36px;
      color: #888;
    }

    button[type="submit"] {
      width: 100%;
      padding: 14px 0;
      background: var(--btn-bg);
      border: none;
      border-radius: 30px;
      color: white;
      font-size: 1.2rem;
      font-weight: bold;
      cursor: pointer;
      transition: background 0.4s ease, transform 0.2s ease;
    }

    button[type="submit"]:hover {
      background: var(--btn-hover);
      transform: scale(1.02);
    }

    .lista {
      max-width: 700px;
      margin: 40px auto;
      background: var(--bg-card);
      padding: 20px 30px;
      border-radius: 12px;
      box-shadow: 0 8px 16px var(--shadow);
      animation: fadeIn 0.8s ease;
    }

    .lista-title {
      font-size: 1.5rem;
      color: var(--accent);
      margin-bottom: 20px;
      border-bottom: 2px solid var(--input-border);
      padding-bottom: 8px;
    }

    .lista div {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 10px 0;
      border-bottom: 1px solid var(--input-border);
    }

    .lista div:last-child {
      border-bottom: none;
    }

    .lista p {
      margin: 0 10px 0 0;
      font-size: 1rem;
      flex: 1;
    }

    .delete-form button {
      padding: 6px 12px;
      background-color: #e53935;
      border: none;
      border-radius: 6px;
      color: white;
      cursor: pointer;
      font-size: 0.9rem;
      transition: background-color 0.3s ease;
    }

    .delete-form button:hover {
      background-color: #c62828;
    }

    .error-message {
      max-width: 500px;
      margin: 10px auto;
      padding: 12px 20px;
      background-color: #ffebee;
      color: #c62828;
      border-left: 5px solid #c62828;
      border-radius: 8px;
      font-weight: bold;
    }

    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(20px); }
      to { opacity: 1; transform: translateY(0); }
    }
  </style>
</head>
<body>

<!-- Botão para alternar entre tema claro e escuro -->
<button class="theme-toggle" onclick="toggleTheme()">
  <i id="theme-icon" class="fas fa-moon"></i>
</button>

<!-- Exibição de mensagens de erro -->
<div class="container-form">
  <h2><i class="fas fa-book"></i> Cadastre os Livros</h2>
  <% if (request.getAttribute("errorMessage") != null) { %>
  <div class="error-message"><%= request.getAttribute("errorMessage") %></div>
  <% } %>

  <!-- Envio dos dados do livro via POST -->
  <form action="Books" method="post">
    <div>
      <label for="titulo">Título</label>
      <i class="fas fa-book input-icon"></i>
      <input type="text" name="titulo" required/>
    </div>
    <div>
      <label for="autor">Autor</label>
      <i class="fas fa-user input-icon"></i>
      <input type="text" name="autor" required/>
    </div>
    <div>
      <label for="ano">Ano</label>
      <i class="fas fa-calendar input-icon"></i>
      <input type="number" name="ano" required/>
    </div>

    <div>
      <label for="isbn">ISBN - Opcional (APENAS NUMEROS)</label>
      <i class="fas fa-barcode input-icon"></i>
      <input type="text" name="isbn" maxlength="13" />
    </div>

    <button type="submit"><i class="fas fa-plus-circle"></i> Cadastrar</button>
  </form>
</div>

<!-- Lista dos livros cadastrados -->
<div class="lista">
  <div class="lista-title"><i class="fas fa-list"></i></div>
  <%= request.getAttribute("htmlBooks") %>
</div>

<!-- Script para alternar tema claro/escuro com salvamento local -->
<script>
  function toggleTheme() {
    document.body.classList.toggle("dark");
    const icon = document.getElementById("theme-icon");
    const isDark = document.body.classList.contains("dark");
    icon.className = isDark ? "fas fa-sun" : "fas fa-moon";
    localStorage.setItem("dark-mode", isDark);
  }

  // Aplica tema escuro
  window.onload = function () {
    const dark = localStorage.getItem("dark-mode") === "true";
    if (dark) {
      document.body.classList.add("dark");
      document.getElementById("theme-icon").className = "fas fa-sun";
    }
  };
</script>

</body>
</html>

