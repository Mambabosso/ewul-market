<template>
  <div class="form-register">
    <h1 class="h2 mb-3 font-weight-normal">{{ $t("register.title") }}</h1>
    <b-form @submit.stop.prevent @submit="register" @reset="reset">
      <div class="row">
        <div class="col-6">
          <label for="username">{{ $t("register.username") }}</label>
          <b-input v-model="username" :state="usernamePossible" id="username" required :readonly="loading"></b-input>
        </div>
        <div class="col-6">
          <label for="email">{{ $t("register.email") }}</label>
          <b-input v-model="email" :state="emailPossible" id="email" required :readonly="loading"></b-input>
        </div>
      </div>
      <hr />
      <div class="row">
        <div class="col-6">
          <label for="password">{{ $t("register.password") }}</label>
          <b-input type="password" v-model="password" :state="passwordPossible" id="password" required :readonly="loading"></b-input>
        </div>
        <div class="col-6">
          <label for="confirm">{{ $t("register.confirm") }}</label>
          <b-input type="password" v-model="confirm" :state="passwordPossible" id="confirm" required :readonly="loading"></b-input>
        </div>
      </div>
      <hr />
      <div v-if="error">
        <b-alert show variant="danger">
          {{ error }}
        </b-alert>
        <hr />
      </div>
      <div v-if="!loading">
        <b-link to="/login">{{ $t("register.loginlink") }}</b-link>
        <b-button class="float-right" type="submit" variant="primary">{{ $t("register.register") }}</b-button>
      </div>
      <div v-else>
        <b-button class="float-right" type="submit" variant="primary" disabled>
          <b-spinner small></b-spinner>
          <span>{{ $t("register.loading") }}</span>
        </b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import register from "./../../services/register";

export default {
  name: "Register",
  data() {
    return {
      loading: false,
      error: null,
      username: "",
      email: "",
      password: "",
      confirm: ""
    };
  },
  methods: {
    register() {
      if (this.isUsernamePossible() && this.isEmailPossible() && this.isPasswordPossible()) {
        this.error = null;
        this.loading = true;
        register(this.username, this.email, this.password)
          .then((result) => {
            if (result.success) {
            }
          })
          .catch((ex) => {
            this.error = this.$t("error." + ex.response.data.error.code);
          })
          .finally(() => {
            this.loading = false;
          });
      }
    },
    reset() {
      this.username = "";
      this.email = "";
      this.password = "";
      this.confirm = "";
    },
    isUsernamePossible() {
      let length = this.username.length;
      return length > 0 && length < 50;
    },
    isEmailPossible() {
      let length = this.email.length;
      return length > 0 && length < 50;
    },
    isPasswordPossible() {
      let length = this.password.length;
      return length > 0 && length < 50 && this.password === this.confirm;
    }
  },
  computed: {
    usernamePossible() {
      return this.isUsernamePossible();
    },
    emailPossible() {
      return this.isEmailPossible();
    },
    passwordPossible() {
      return this.isPasswordPossible();
    }
  }
};
</script>

<style scoped>
body {
  display: flex;
  align-items: center;
  padding: 40px;
}
.form-register {
  width: 100%;
  max-width: 580px;
  padding: 16px;
  margin: auto;
}
</style>
