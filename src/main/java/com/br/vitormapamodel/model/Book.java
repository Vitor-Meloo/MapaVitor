package com.br.vitormapamodel.model;
import com.br.vitormapamodel.exceptions.BookException;
import com.br.vitormapamodel.utils.ISBNGenerator;

/* Classe Book, com as informações principais dos Livros */
public class Book {
    private static int contador = 1;
    private final int id;
    private final String isbn;
    private String title;
    private String author;
    private int year;

    /*Construtor do livro, Gera ID único */
    public Book(String title, String author, int year, String isbnInput) throws BookException {
        this.id = contador++;
        this.setTitle(title);
        this.setAuthor(author);
        this.setYear(year);

        // Gera ISBN automaticamente se não for informado
        this.isbn = (isbnInput == null || isbnInput.trim().isEmpty())
                ? ISBNGenerator.ISBNGen()
                : validateISBN(isbnInput.trim());

        validateerrors();
    }

    // Valida se o ISBN possui 10 ou 13 dígitos numéricos
    private String validateISBN(String isbn) throws BookException {
        if (!isbn.matches("\\d{10}|\\d{13}")) {
            throw new BookException("ISBN inválido. Deve conter 10 ou 13 dígitos numéricos.");
        }
        return isbn;
    }

    // Valida título, autor e ano
    private void validateerrors() throws BookException {
        if (title == null || title.trim().isEmpty()) {
            throw new BookException("O título é obrigatório.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new BookException("O autor é obrigatório.");
        }
        if (year < 1000 || year > 2025) {
            throw new BookException("Ano inválido.");
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title.trim().toUpperCase();
    }

    public void setAuthor(String author) {
        this.author = author.trim().toUpperCase();
    }

    public void setYear(int year) {
        this.year = year;
    }
}
