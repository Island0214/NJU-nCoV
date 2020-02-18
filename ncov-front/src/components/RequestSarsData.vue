<template>
  <el-main>
    <el-select v-model="country_value" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>

    <div class="block">
      <br>
      <span class="demonstration"></span>
      <div class="demonstration"></div>
      <el-date-picker
        v-model="value_date"
        type="date"
        placeholder="选择日期"
        format="yyyy 年 MM 月 dd 日"
        value-format="yyyy-MM-dd">
      </el-date-picker>
    </div>

    <br/>
    <el-button type="primary" v-on:click="query">查询</el-button>
    <p>数量：确诊-死亡-疑似</p>
    <p>确诊：{{sars.confirm}}</p>
    <p>死亡：{{sars.dead}}</p>
    <p>疑似：{{sars.suspect}}</p>
  </el-main>
</template>

<script>
  export default {
    name: 'RequestSarsData',
    data: function () {
      return {
        DataForm: {
          country: '',
          year: '',
          month: '',
          day: ''
        },
        responseResult: [],
        options: [{
          value: 'China',
          label: '中国'
        }, {
          value: 'Japan',
          label: '日本'
        }, {
          value: 'South-Korea',
          label: '韩国'
        }, {
          value: 'North-Korea',
          label: '朝鲜'
        }],
        country_value: '',
        value_date: '',
        date_year: '',
        date_month: '',
        date_day: '',
        sars: {
          confirm: '',
          dead: '',
          suspect: '',
        }
      }
    },
    methods: {
      query() {
        this.date_year = this.value_date.split('-')[0]
        this.date_month = parseInt(this.value_date.split('-')[1]).toString()
        this.date_day = parseInt(this.value_date.split('-')[2]).toString()
        var data_string = ''
        this.$axios
          .post('/sars', {
            country: this.country_value,
            year: this.date_year,
            month: this.date_month,
            day: this.date_day
          })
          .then(successResponse => {
            data_string = successResponse.data
            this.sars.confirm = data_string.split('-')[0]
            this.sars.dead = data_string.split('-')[1]
            this.sars.suspect = data_string.split('-')[2]
          })
          // eslint-disable-next-line no-unused-vars
          .catch(failResponse => {
          })
      }
    }
  }
</script>
