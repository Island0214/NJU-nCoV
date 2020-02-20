<template>
    <div class="chart-wrapper" ref="lineChart">

    </div>
</template>

<script>
    import echarts from "echarts";

    export default {
        name: "LineChart",
        props: ['data'],
        data() {
            return {
                option:
                    {
                        xAxis: {
                            type: 'category',
                            data: [],
                            axisLine: {
                                lineStyle: {
                                    color: '#FFFFFF',
                                    width: 2
                                }
                            },
                            boundaryGap: false,
                        },
                        yAxis: {
                            type: 'value',
                            axisLine: {
                                lineStyle: {
                                    color: '#FFFFFF',
                                    width: 2
                                }
                            }
                        },
                        legend: {
                            data: [],
                            textStyle: {
                                color: '#FFFFFF',
                                fontWeight: 'bolder'
                            },
                            icon: 'pin'
                        },
                        grid: {
                            show: true,
                            backgroundColor: '#303843'
                        },
                        color: ['#646299', '#afcaed'],
                        series: [],
                        tooltip: {
                            show: true,
                            trigger: 'axis'
                        }
                    }

            }
        },
        watch: {
          data: function (data) {
              this.chartConfigure(data);
          }
        },
        methods: {
            chartConfigure(data) {
                this.option.xAxis.data = data.xAxis;
                let series = [];
                let legends = [];
                for (let i = 0; i < data.series.length; i++) {
                    series.push({
                        type: 'line',
                        data: data.series[i].data,
                        name: data.series[i].name
                    });
                    legends.push({
                        name: data.series[i].name
                    });
                }
                this.option.series = series;
                this.option.legend.data = legends;
                let myChart = echarts.init(this.$refs.lineChart);
                myChart.setOption(this.option);
            }
        },
        mounted() {
            this.chartConfigure(this.data);
        }
    }
</script>

<style scoped>
    .chart-wrapper {
        width: 100%;
        height: 50vh;
    }
</style>