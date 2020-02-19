<template>
    <div class="epidemic">
        <div>
            <div class="map-wrapper" ref="myEchart"></div>
            <div class="content-wrapper">
                <el-row>
                    <el-col :span="12">
                        <div class="data-wrapper">
                            <h1>实时疫情动态</h1>
                            <p>数据截止2020-02-19 18:58</p>
                            <h1>——</h1>

                            <h4>累计确诊人数</h4>
                            <h2>{{statistic.totalDiagnosis}}<span> 较昨日<strong>{{statistic.totalDiagnosisIncrement > 0 ? '+' + statistic.totalDiagnosisIncrement: statistic.totalDiagnosisIncrement}}</strong></span></h2>
                            <h4>现存确诊人数</h4>
                            <h2>{{statistic.currentDiagnosis}}<span> 较昨日<strong>{{statistic.currentDiagnosisIncrement > 0 ? '+' + statistic.currentDiagnosisIncrement: statistic.currentDiagnosisIncrement}}</strong></span></h2>
                            <h4>现存疑似人数</h4>
                            <h2>{{statistic.currentSuspect}}<span> 较昨日<strong>{{statistic.currentSuspectIncrement > 0 ? '+' + statistic.currentSuspectIncrement: statistic.currentSuspectIncrement}}</strong></span></h2>
                            <h4>累计死亡人数</h4>
                            <h2>{{statistic.totalDeath}}<span> 较昨日<strong>{{statistic.totalDeathIncrement > 0 ? '+' + statistic.totalDeathIncrement: statistic.totalDeathIncrement}}</strong></span></h2>
                            <h4>累计治愈人数</h4>
                            <h2>{{statistic.totalHealing}}<span> 较昨日<strong>{{statistic.totalHealingIncrement > 0 ? '+' + statistic.totalHealingIncrement: statistic.totalHealingIncrement}}</strong></span></h2>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="area-wrapper">
                            <h4>感染人数</h4>
                            <p>Number of Infected People</p>
                            <h1>——</h1>
                            <h2>{{area.name}}<br>{{area.value}}</h2>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

<script>
    import echarts from "echarts";
    import '../../node_modules/echarts/map/js/china.js'

    export default {
        name: "Epidemic",
        data() {
            return {
                statistic: {
                    totalDiagnosis: 74282,
                    totalDiagnosisIncrement: 1756,
                    currentDiagnosis: 57337,
                    currentDiagnosisIncrement: -760,
                    currentSuspect: 5248,
                    currentSuspectIncrement: 1185,
                    totalDeath: 2009,
                    totalDeathIncrement: 139,
                    totalHealing: 14938,
                    totalHealingIncrement: 1377,
                },
                area: {
                    name: '全国',
                    value: 74282
                },
                country: {
                    name: '全国',
                    value: 74282
                }
            }
        },
        methods: {
            chinaConfigure() {
                let that = this;
                let myChart = echarts.init(this.$refs.myEchart);
                window.onresize = myChart.resize;
                myChart.setOption({
                    tooltip: {
                        show: false
                    }, // 鼠标移到图里面的浮动提示框
                    dataRange: {
                        show: false,
                        min: 0,
                        max: 1000,
                        text: ['High', 'Low'],
                        realtime: true,
                        calculable: true,
                        // color: ['#1a4474', '#096dd9', '#5cdbd3', '#73d13d', '#a0d911', '#fff566']
                        // color: ['#10239e', '#0050b3', '#5f7685']
                        color: ['#494775', '#414f75', '#5f7685']
                    },
                    geo: { // 这个是重点配置区
                        map: 'china', // 表示中国地图
                        roam: false,
                        label: {
                            normal: {
                                show: true, // 是否显示对应地名
                                textStyle: {
                                    color: 'rgba(0,0,0,0.4)'
                                }
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#303843',
                                borderColor: '#333333'
                            },
                            emphasis: {
                                areaColor: null,
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowBlur: 0,
                                borderWidth: 0,
                                color: '#607789',
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    },
                    series: [{
                        type: 'scatter',
                        coordinateSystem: 'geo' // 对应上方配置
                    },
                        {
                            name: '启动次数',
                            type: 'map',
                            geoIndex: 0,
                            data: [{
                                "name": "北京",
                                "value": 599
                            }, {
                                "name": "上海",
                                "value": 142
                            }, {
                                "name": "黑龙江",
                                "value": 44
                            }, {
                                "name": "深圳",
                                "value": 92
                            }, {
                                "name": "湖北",
                                "value": 810
                            }, {
                                "name": "四川",
                                "value": 453
                            }]
                        }
                    ]
                });

                myChart.on('mouseover', function (params) {
                    that.currentData(params);
                });

                myChart.on('mouseout', function () {
                    that.currentData(that.country);
                });
            },
            currentData(param) {
                this.area = {
                    name: param.name,
                    value: isNaN(param.value) ? 0 : param.value,
                };
            }
        },
        mounted() {
            this.chinaConfigure();
        }
    }
</script>

<style scoped>
    .map-wrapper {
        height: 100vh;
        width: 100%;
    }

    .content-wrapper {
        position: absolute;
        top: 0;
        left: 30px;
        right: 30px;
    }

    .data-wrapper {
        text-align: left;
        position: absolute;
        top: 15vh;
    }

    .area-wrapper {
        text-align: right;
        position: absolute;
        top: 80vh;
        right: 0;
    }

    h2 {
        margin-bottom: 10px;
    }

    h2 span {
        font-size: 10px;
    }

    h2 span strong {
        color: #FFFFFF;
    }
</style>