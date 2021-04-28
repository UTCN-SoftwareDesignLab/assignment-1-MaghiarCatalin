<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addUser">Add User</v-btn>
      <v-btn @click="deleteUser">Delete User</v-btn>
      <v-btn @click="editUser">Edit User</v-btn>
      <v-btn @click="booksAdmin">Book</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
      @click = "editUser"
    ></v-data-table>
    <UserDialog
        :opened="dialogVisible"
        :user="selectedUser"
        @refresh="refreshList"
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "../components/UserDialog";
import router from "../router";
export default {
  name: "UserList",
  components: { UserDialog },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        {
          text: "Username",
          align: "start",
          sortable: false,
          value: "name",
        },
        {text: "Email", value: "email"},
        {text: "Roles", value: "roles"},
      ],
      dialogVisible: false,
      selectedUser: {},
    };
  },
  methods: {
    booksAdmin(){
      router.push("./booksAdmin");
    },
    editUser(user) {
      this.selectedUser = user;
      this.dialogVisible = true;
    },
    deleteUser(user) {
      this.selectedUser = user;
      this.dialogVisible = true;
    },
    addUser() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.users = await api.users.allUsers();
    },
  },
  async created() {
    this.users = await api.users.allUsers();
  },
};
</script>

<style scoped></style>