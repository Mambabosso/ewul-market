<template>
  <div class="dropdown-link">
    <button class="btn btn-link" @click="toggle()"><slot></slot></button>
    <div v-if="menuVisible"></div>
  </div>
</template>

<script>
export default {
  name: "DropdownLink",
  data() {
    return {
      menuVisible: false
    };
  },
  props: {
    items: {
      type: Array,
      required: true
    }
  },
  methods: {
    toggle() {
      this.menuVisible = !this.menuVisible;
    }
  }
};
</script>

<style scoped>
.dropdown-link {
  user-select: none;
}
</style>
