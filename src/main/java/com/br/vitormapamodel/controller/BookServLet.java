package com.br.vitormapamodel.controller;
import com.br.vitormapamodel.exceptions.BookException;
import com.br.vitormapamodel.utils.HTMLGenerator;
import com.br.vitormapamodel.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Servlet responsável por listar, adicionar e excluir livros */
@WebServlet(name = "BookServlet", value = "/Books")
public class BookServLet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static List<Book> books;

    public static List<Book> getBooks() {
        return books;
    }

    @Override
    public void init() {
        books = new ArrayList<>();
    }

    /* Metodo POST para adicionar e excluir um livro*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String htmlBooks = HTMLGenerator.generate(request, books);
        request.setAttribute("htmlBooks", htmlBooks);
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
    }

    /*Trata requisições do tipo POST E Gera o HTML da lista de livros e encaminha para a página JSP */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Exclusão de livro pelo ID
        if ("delete".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    int id = Integer.parseInt(idParam);
                    books.removeIf(book -> book.getId() == id);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "ID inválido para exclusão.");
                }
            }
            response.sendRedirect(request.getContextPath() + "/Books");
            return;
        }

        // Adição de novo livro
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anoStr = request.getParameter("ano");
        String isbn = request.getParameter("isbn");

        //Tratamento de erros e exceptions
        try {
            int ano = Integer.parseInt(anoStr);
            Book book = new Book(titulo, autor, ano, isbn);
            books.add(book);
            response.sendRedirect(request.getContextPath() + "/Books");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "O campo ano deve conter apenas números.");
            request.setAttribute("htmlBooks", HTMLGenerator.generate(request, books));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        } catch (BookException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("htmlBooks", HTMLGenerator.generate(request, books));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        }
    }
}
