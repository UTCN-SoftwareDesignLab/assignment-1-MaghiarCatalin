package com.lab4.demo.report;

import com.lab4.demo.book.BookService;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

import static com.lab4.demo.report.ReportType.PDF;

@Service
@AllArgsConstructor
public class PdfReportService implements ReportService {
    private final BookService bookService;

    @Override
    public String export() {
        try {

            List<BookDTO> books = bookService.findOutOfStock();

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();

            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset(-200, -100);
            contentStream.newLineAtOffset(300, 800);
            contentStream.setLeading(20);
            contentStream.showText("LIST OF BOOKS OUT OF STOCK");
            contentStream.newLine();
            contentStream.newLine();

            for (BookDTO book : books) {

                contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);

                contentStream.showText("id: " + book.getId());
                contentStream.newLine();
                contentStream.showText("title: " + book.getTitle());
                contentStream.newLine();
                contentStream.showText("author: " + book.getAuthor());
                contentStream.newLine();
                contentStream.showText("genre: " + book.getGenre());
                contentStream.newLine();
                contentStream.showText("price: " + book.getPrice());
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save("OutOfStockBooks.pdf");
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "PDF reporter done!";
    }

    @Override
    public ReportType getType() {
        return PDF;
    }
}
