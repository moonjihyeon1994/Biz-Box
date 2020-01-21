<template>
<v-col cols="6">
  <v-list>
    <v-list-item>
      <v-icon large>mdi-home-variant-outline</v-icon>: {{ info.major01 }}
    </v-list-item>
    <v-list-item>
      <v-icon large>mdi-store</v-icon>: {{ info.major02 }}
    </v-list-item>
    <v-list-item>
      <v-icon large>mdi-hospital-box-outline</v-icon>: {{ info.major03 }}
    </v-list-item>
    <v-list-item>
      <v-icon large>mdi-office-building</v-icon>: {{ info.major04 }}
    </v-list-item>
    <v-list-item>
      <v-icon large>mdi-silverware-fork-knife</v-icon>: {{ info.major05 }}
    </v-list-item>
    <v-list-item>
      <v-icon large>mdi-target</v-icon>: {{ info.target }}
    </v-list-item>
  </v-list>
</v-col>
  </template>

<script>

export default {
  name: 'resultList',
  data () {
    return {
      info: {
        'major01': '521112',
        'major02': '307888',
        'major03': '213230',
        'major04': '27767',
        'major05': '93477',
        'target': '323'
      }
    }
  }
}
</script>
