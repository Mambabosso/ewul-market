<template>
  <div class="form-login">
    <h1 class="h2 mb-3 font-weight-normal">{{ $t("login.title") }}</h1>
    <b-form @submit.stop.prevent @submit="login" @reset="reset">
      <div class="input">
        <label for="username">{{ $t("login.username") }}</label>
        <b-input v-model="username" id="username" required :readonly="loading"></b-input>
        <hr />
        <label for="password">{{ $t("login.password") }}</label>
        <b-input type="password" v-model="password" id="password" required :readonly="loading"></b-input>
      </div>
      <hr />
      <div v-if="!loading">
        <b-link to="/register">{{ $t("login.registerlink") }}</b-link>
        <b-button class="float-right" type="submit" variant="primary">{{ $t("login.login") }}</b-button>
      </div>
      <div v-else>
        <b-button class="float-right" type="submit" variant="primary" disabled>
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
  }
};
</script>

<style scoped>
body {
  display: flex;
  align-items: center;
  padding: 40px;
}
.form-login {
  width: 100%;
  max-width: 380px;
  padding: 16px;
  margin: auto;
}
</style>
