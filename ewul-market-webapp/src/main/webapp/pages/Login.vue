<template>
  <div class="form-signin">
    <h1 class="h2 mb-3 font-weight-normal">{{ $t("login.title") }}</h1>
    <b-form @submit.stop.prevent @submit="login" @reset="reset">
      <div class="input my-4">
        <label for="username">{{ $t("login.username") }}</label>
        <b-input v-model="username" :state="usernamePossible" id="username" required :readonly="loading"></b-input>
        <hr />
        <label for="password">{{ $t("login.password") }}</label>
        <b-input type="password" v-model="password" :state="passwordPossible" id="password" required :readonly="loading"></b-input>
      </div>
      <div v-if="!loading">
        <b-button type="submit" variant="primary">{{ $t("login.login") }}</b-button>
        <b-button type="reset" variant="warning">{{ $t("login.reset") }}</b-button>
      </div>
      <div v-else>
        <b-button type="submit" variant="primary" disabled>
          <b-spinner small></b-spinner>
          <span>{{ $t("login.loading") }}</span>
        </b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loading: false,
      username: "",
      password: ""
    };
  },
  methods: {
    login() {
      if (this.isUsernamePossible() && this.isPasswordPossible()) {
      }
    },
    sessionLogin() {},
    reset() {
      this.username = "";
      this.password = "";
    },
    isUsernamePossible() {
      let length = this.username.length;
      return length > 0 && length < 50;
    },
    isPasswordPossible() {
      let length = this.password.length;
      return length > 0 && length < 50;
    }
  },
  computed: {
    usernamePossible() {
      return this.isUsernamePossible();
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
  padding-top: 40px;
  padding-bottom: 40px;
}
.form-signin {
  width: 100%;
  max-width: 350px;
  padding: 16px;
  margin: auto;
}
</style>
