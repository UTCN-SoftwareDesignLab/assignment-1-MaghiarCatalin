<template>
  <v-card>
    <v-card-title>
      Books
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addBook">Add Book</v-btn>
      <v-btn @click="deleteBook">Delete Book</v-btn>
      <v-btn @click="editBook">Edit Book</v-btn>
      <v-btn @click="exportCSV">CSV Report</v-btn>
      <v-btn @click="exportPDF">PDF Report</v-btn>
      <v-btn @click="user">User</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="books"
        :search="search"
        @click:row="editBook"
    ></v-data-table>
    <BookDialog
        :opened="dialogVisible"
        :book="selectedBook"
        @refresh="refreshList"
    ></BookDialog>
  </v-card>
</template>

<script>
import api from "../api";
import BookDialog from "../components/BookDialog";
import router from "../router";
export default {
  name: "BookList",
  components: { BookDialog },
  data() {
    return {
      books: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Author", value: "author" },
        { text: "Genre", value: "genre" },
        { text: "Price", value: "price" },
        { text: "Quantity", value: "quantity" },
      ],
      dialogVisible: false,
      selectedBook: {},
    };
  },
  methods: {
      user(){
        router.push("./user");
      },
      editBook(book) {
        this.selectedBook = book;
        this.dialogVisible = true;
      },
      deleteBook(book){
        this.selectedBook = book;
        this.dialogVisible = true;
      },
      addBook() {
        this.dialogVisible = true;
      },
      exportCSV() {
      api.books.csv();
    },
    exportPDF() {
      api.books.pdf();
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedBook = {};
      this.books = await api.books.allBooks();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>