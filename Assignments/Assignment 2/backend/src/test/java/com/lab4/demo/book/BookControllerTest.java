package com.lab4.demo.book;
import com.lab4.demo.BaseControllerTest;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.report.CSVReportService;
import com.lab4.demo.report.PdfReportService;
import com.lab4.demo.report.ReportServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static com.lab4.demo.TestCreationFactory.randomBoundedInt;
import static com.lab4.demo.TestCreationFactory.randomString;
import static com.lab4.demo.UrlMapping.*;
import static com.lab4.demo.report.ReportType.CSV;
import static com.lab4.demo.report.ReportType.PDF;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest extends BaseControllerTest {
    @InjectMocks
    private BookController controller;

    @Mock
    private BookService bookService;

    @Mock
    private ReportServiceFactory reportServiceFactory;

    @Mock
    private CSVReportService csvReportService;

    @Mock
    private PdfReportService pdfReportService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        controller = new BookController(bookService, reportServiceFactory);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allItems() throws Exception {
        List<BookDTO> books = TestCreationFactory.listOf(Book.class);
        when(bookService.findAll()).thenReturn(books);

        ResultActions response = mockMvc.perform(get(BOOKS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(books));

    }

    @Test
    void exportReport() throws Exception {
        when(reportServiceFactory.getReportService(PDF)).thenReturn(pdfReportService);
        when(reportServiceFactory.getReportService(CSV)).thenReturn(csvReportService);
        String pdfResponse = "";
        when(pdfReportService.export()).thenReturn(pdfResponse);
        String csvResponse = "";
        when(csvReportService.export()).thenReturn(csvResponse);

        ResultActions pdfExport = mockMvc.perform(get(BOOKS + EXPORT_REPORT, PDF.name()));
        ResultActions csvExport = mockMvc.perform(get(BOOKS + EXPORT_REPORT, CSV.name()));

        pdfExport.andExpect(status().isOk())
                .andExpect(content().string(pdfResponse));
        csvExport.andExpect(status().isOk())
                .andExpect(content().string(csvResponse));

    }

    @Test
    void create() throws Exception {
        BookDTO bookRequest = BookDTO.builder()
                .id(5)
                .title("Title1")
                .author("Author1")
                .genre("Genre1")
                .price(50)
                .quantity(10)
                .build();

        when(bookService.create(bookRequest)).thenReturn(bookRequest);

        ResultActions result = performPostWithRequestBody(BOOKS, bookRequest);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(bookRequest));
    }

    @Test
    void update() throws Exception {
        int id = randomBoundedInt(Integer.MAX_VALUE);
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .title(randomString())
                .author(randomString())
                .build();

        when(bookService.update(id, reqItem)).thenReturn(reqItem);

        ResultActions result = performPutWithRequestBodyAndPathVariable(BOOKS + ENTITY, reqItem, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }

    @Test
    void delete() throws Exception {
        BookDTO bookRequest = BookDTO.builder()
                .id(5)
                .title("Title1")
                .author("Author1")
                .genre("Genre1")
                .price(50)
                .quantity(10)
                .build();
        Integer id = bookRequest.getId();
        ResultActions result = performDeleteWithPathVariable(BOOKS + ENTITY, id.toString());
        result.andExpect(status().isOk());
    }
}