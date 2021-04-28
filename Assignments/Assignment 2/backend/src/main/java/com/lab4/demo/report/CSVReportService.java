package com.lab4.demo.report;

import com.lab4.demo.book.BookService;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.lab4.demo.report.ReportType.CSV;

@Service
@AllArgsConstructor
public class CSVReportService implements ReportService {
    private final BookService bookService;

    @Override
    public String export() {
        try {

            List<BookDTO> books = bookService.findOutOfStock();

            FileWriter fileWriter;
            fileWriter = new FileWriter("OutOfStockBooks.csv");
            fileWriter.append("id, title, author, genre, price");
            fileWriter.append("\n");

            for (BookDTO book : books) {
                fileWriter.append(book.getId() + "");
                fileWriter.append(",");
                fileWriter.append(book.getTitle());
                fileWriter.append(",");
                fileWriter.append(book.getAuthor());
                fileWriter.append(",");
                fileWriter.append(book.getGenre());
                fileWriter.append(",");
                fileWriter.append(book.getPrice() + "");
                fileWriter.append(",");
                fileWriter.append("\n");
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "CSV reporter done!";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
