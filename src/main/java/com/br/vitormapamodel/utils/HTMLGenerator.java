package com.br.vitormapamodel.utils;

import com.br.vitormapamodel.model.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/* Classe para gerar o HTML dinâmico da lista de livros */
public class HTMLGenerator {

    public static String generate(HttpServletRequest request, List<Book> books) {
        StringBuilder htmlLivros = new StringBuilder();
        htmlLivros.append("<h2>Livros cadastrados:</h2>");

        for (Book book : books) {
            htmlLivros
                    .append("<div class=\"lista\">")
                    .append("<p><strong> Titulo: </strong>").append(book.getTitle()).append("</p>")
                    .append("<p><strong> Autor: </strong>").append(book.getAuthor()).append("</p>")
                    .append("<p><strong> Ano: </strong>").append(book.getYear()).append("</p>")
                    .append("<p><strong> ISBN: </strong>").append(book.getIsbn()).append("</p>")
                    .append("<p><strong> id: </strong>").append(book.getId()).append("</p>")

                    // Formulário de exclusão individual por livro
                    .append("<form class=\"delete-form\" method='post' action='")
                    .append(request.getContextPath()).append("/Books' style='display:inline;'>")
                    .append("<input type='hidden' name='action' value='delete' />")
                    .append("<input type='hidden' name='id' value='").append(book.getId()).append("' />")
                    .append("<button type='submit'>EXCLUIR</button>")
                    .append("</form>")
                    .append("</div>");
        }

        return htmlLivros.toString();
    }
}
