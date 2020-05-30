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
          <b-input v-model="password" :state="passwordPossible" id="password" required :readonly="loading"></b-input>
        </div>
        <div class="col-6">
          <label for="confirm">{{ $t("register.confirm") }}</label>
          <b-input v-model="confirm" :state="passwordPossible" id="confirm" required :readonly="loading"></b-input>
        </div>
      </div>
      <hr />
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
export default {
  name: "Register",
  data() {
    return {
      loading: false,
      username: "",
      email: "",
      password: "",
      confirm: ""
    };
  },
  methods: {
    register() {
      if (this.isUsernamePossible() && this.isEmailPossible() && this.isPasswordPossible()) {
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
      let length1 = this.password.length;
      let length2 = this.confirm.length;
      return length1 > 0 && length1 < 50 && length2 > 0 && length2 < 50 && this.password === this.confirm;
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
