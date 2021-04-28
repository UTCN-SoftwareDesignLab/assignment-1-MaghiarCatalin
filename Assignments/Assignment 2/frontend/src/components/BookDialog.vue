<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create item" : "Edit item" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="book.title" label="Title" />
          <v-text-field v-model="book.author" label="Author" />
          <v-text-field v-model="book.genre" label="Genre" />
          <v-text-field v-model="book.price" label="Price" />
          <v-text-field v-model="book.quantity" label="Quantity" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "BookDialog",
  props: {
    book: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.books
            .create({
              name: this.book.name,
              author: this.book.author,
              genre: this.book.genre,
              price: this.book.price,
              quantity: this.book.quantity,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.books
            .edit(this.book.id, {
              id: this.book.id,
              name: this.book.name,
              author: this.book.author,
              genre: this.book.genre,
              price: this.book.price,
              quantity: this.book.quantity,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    deleteBook() {
      api.books.delete(this.book.id)
          .then((response) => {
            if (response == true)
              this.$emit("refresh")
          });
    },
  },
  sell() {
    if (this.book.quantity >= 1) {
      api.books
          .sell(this.book.id)
          .then(() => this.$emit("refresh"));
    } else {
      this.$alert("Out of stock!")
          .then(() => this.$emit("refresh"));
    }
  },


  computed: {
    isNew: function () {
      return !this.book.id;
    },
  },
};
</script>

<style scoped></style>