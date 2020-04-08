<template>
    <div class="epidemic">
        <div>
            <div class="map-wrapper" ref="myEchart"></div>
            <div class="content-wrapper">
                <el-row>
                    <el-col :span="12">
                        <div class="data-wrapper">
                            <h1>实时疫情动态</h1>
                            <p>数据截止{{this.curTime}}</p>
                            <h1>——</h1>

                            <h4>累计确诊人数</h4>
                            <h2>{{statistic.totalDiagnosis}}<span> 较昨日<strong>{{statistic.totalDiagnosisIncrement > 0 ? '+' + statistic.totalDiagnosisIncrement: statistic.totalDiagnosisIncrement}}</strong></span>
                            </h2>
                            <h4>现存确诊人数</h4>
                            <h2>{{statistic.currentDiagnosis}}<span> 较昨日<strong>{{statistic.currentDiagnosisIncrement > 0 ? '+' + statistic.currentDiagnosisIncrement: statistic.currentDiagnosisIncrement}}</strong></span>
                            </h2>
                            <h4>现存疑似人数</h4>
                            <h2>{{statistic.currentSuspect}}<span> 较昨日<strong>{{statistic.currentSuspectIncrement > 0 ? '+' + statistic.currentSuspectIncrement: statistic.currentSuspectIncrement}}</strong></span>
                            </h2>
                            <h4>累计死亡人数</h4>
                            <h2>{{statistic.totalDeath}}<span> 较昨日<strong>{{statistic.totalDeathIncrement > 0 ? '+' + statistic.totalDeathIncrement: statistic.totalDeathIncrement}}</strong></span>
                            </h2>
                            <h4>累计治愈人数</h4>
                            <h2>{{statistic.totalHealing}}<span> 较昨日<strong>{{statistic.totalHealingIncrement > 0 ? '+' + statistic.totalHealingIncrement: statistic.totalHealingIncrement}}</strong></span>
                            </h2>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="area-wrapper">
                            <!-- <h4>感染人数</h4>
                            <p>Number of Infected People</p>
                            <h1>——</h1>
                            <h2>{{area.name}}<br>{{area.value}}</h2> -->
                            <h2>{{area.name}}</h2>
                            <h1>——</h1>
                            <h4>总感染人数</h4>
                            <p>Number of Infected People</p>
                            <h2>{{area.value}}</h2>
                            <h4>治愈人数</h4>
                            <p>Number of Cured People</p>
                            <h2>{{area.cured}}</h2>
                            <h4>剩余感染人数</h4>
                            <p>Number of Current Infected People</p>
                            <h2>{{area.current}}</h2>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
        <div class="divider-wrapper" style="margin-top: -9vh">
            <el-divider></el-divider>
        </div>
        <div class="tables-wrapper">
            <el-row>
                <el-col :span="6">
                    <div class="select-wrapper">
                        <h1>疫情发展推移<br>——</h1>
                        <el-radio v-model="chartType" label="new">全国新增确诊/疑似人数</el-radio>
                        <el-radio v-model="chartType" label="current">全国现存确诊/疑似人数</el-radio>
                        <el-radio v-model="chartType" label="finish">全国死亡/治愈人数</el-radio>
                        <el-radio v-model="chartType" label="total">全国疫情累计趋势图</el-radio>
                    </div>
                </el-col>
                <el-col :span="18">
                    <!-- <StackAreaChart :data="chartData" v-if="chartType === 'total'"></StackAreaChart> -->
                    <StackAreaChart :data="chartData" v-if="chartType === 'stack'"></StackAreaChart>
                    <LineChart :data="chartData" v-else></LineChart>
                </el-col>
            </el-row>
        </div>
        <!--<el-divider></el-divider>-->

        <div class="divider-wrapper" style="margin-top: 2vh">
            <el-divider></el-divider>
        </div>

        <h1>国家 / 中国省份数据</h1>
        <br>
        <el-select v-model="countrySelect" placeholder="国家">
            <el-option
                    v-for="item in countryList"
                    :key="item"
                    :label="item"
                    :value="item">
            </el-option>
        </el-select>

        <el-select v-model="provinceSelect" placeholder="省份" v-if="countrySelect === '中国'">
            <el-option
                    v-for="item in provinceList"
                    :key="item"
                    :label="item"
                    :value="item">
            </el-option>
        </el-select>

        <br>
        <br>
        <br>
        <h2>{{currentChartName}}</h2>

        <LineChart :data="countryArray"></LineChart>

        <Footer></Footer>
    </div>
</template>

<script>
    import echarts from "echarts";
    import '../../node_modules/echarts/map/js/china.js'
    import LineChart from '../components/charts/LineChart.vue'
    import StackAreaChart from '../components/charts/StackAreaChart.vue'
    import Footer from '../components/Footer.vue'
    import {api} from "../request/api";

    export default {
        name: "Epidemic",
        components: {
            LineChart,
            StackAreaChart,
            Footer
        },
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
                    value: 74282,
                    cured: 0,
                    current: 0
                },
                country: {
                    name: '全国',
                    value: 74282,
                    cured: 0,
                    current: 0
                },
                chartData: {
                    xAxis: ['2020-02-13', '2020-02-14', '2020-02-15', '2020-02-16', '2020-02-17', '2020-02-18', '2020-02-19'],
                    series: [{
                        name: '新增确诊人数',
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }, {
                        name: '新增疑似人数',
                        data: [100, 1213, 122, 342, 1236, 41, 123]
                    }]
                },
                provinceName: {
                    name: ['黑龙江', '辽宁', '吉林', '北京', '天津', '河北', '内蒙古', '宁夏', '山西', '陕西', '山东', '江苏', '安徽', '上海',
                        '浙江', '福建', '河南', '湖南', '湖北', '江西', '广东', '广西', '海南', '澳门', '香港', '台湾', '四川', '贵州', '云南', '重庆',
                        '甘肃', '青海', '西藏', '新疆']
                },
                provinceData: [
                    // {"name":"山东", "value":"123"}, {"name":"江苏", "value":"999"}
                ],
                chartType: 'new',
                curTime: '2020-02-24 18:00',
                countrySerialData: {
                    xAxis: [],
                    series: [
                        {
                            name: '确诊人数',
                            data: []
                        }, {
                            name: '治愈人数',
                            data: []
                        }, {
                            name: '死亡人数',
                            data: []
                        }
                    ]
                },
                newSerialData: {
                    xAxis: [],
                    series: [
                        {
                            name: '新增确诊人数',
                            data: []
                        }, {
                            name: '新增疑似人数',
                            data: []
                        }
                    ]
                },
                remainData: {
                    xAxis: [],
                    series: [
                        {
                            name: '现存确诊人数',
                            data: []
                        }, {
                            name: '现存疑似人数',
                            data: []
                        }
                    ]
                },
                finishData: {
                    xAxis: [],
                    series: [
                        {
                            name: '治愈人数',
                            data: []
                        }, {
                            name: '死亡人数',
                            data: []
                        }
                    ]
                },
                countrySelect: '中国',
                provinceSelect: '',
                citySelect: '',
                countryList: [
                    "中国", "日本", "韩国", "美国", "英国", "阿尔巴尼亚", "阿尔及利亚", "阿富汗", "阿根廷", "阿联酋", "阿鲁巴", "阿曼", "阿塞拜疆", "埃及", "埃塞俄比亚",
                    "爱尔兰", "爱沙尼亚", "安道尔", "安哥拉", "安圭拉", "安提瓜和巴布达", "奥地利", "澳大利亚", "巴巴多斯", "巴布亚新几内亚", "巴哈马",
                    "巴基斯坦", "巴拉圭", "巴勒斯坦", "巴林", "巴拿马", "巴西", "白俄罗斯", "百慕大", "保加利亚", "北马里亚纳", "北马其顿", "贝宁",
                    "比利时", "冰岛", "玻利维亚", "波多黎各", "波黑", "波兰", "博茨瓦纳", "伯利兹", "不丹", "布基纳法索", "布隆迪", "赤道几内亚",
                    "丹麦", "德国", "东帝汶", "多哥", "多米尼加", "多米尼克", "俄罗斯", "厄瓜多尔", "厄立特里亚", "法国", "法罗群岛", "法属波利尼西亚",
                    "法属圭亚那", "菲律宾", "芬兰", "佛得角", "冈比亚", "刚果（布）", "刚果（金）", "哥伦比亚", "哥斯达黎加", "格恩西岛", "格林纳达",
                    "格陵兰", "格鲁吉亚", "古巴", "瓜德罗普", "关岛", "圭亚那", "哈萨克斯坦", "海地", "荷兰", "荷属安的列斯", "黑山", "洪都拉斯",
                    "吉布提", "吉尔吉斯斯坦", "几内亚", "几内亚比绍", "加拿大", "加纳", "加蓬", "柬埔寨", "捷克", "津巴布韦", "喀麦隆", "卡塔尔", "开曼群岛",
                    "科特迪瓦", "科威特", "克罗地亚", "肯尼亚", "拉脱维亚", "老挝", "黎巴嫩", "利比里亚", "利比亚", "立陶宛", "列支敦士登", "留尼汪", "卢森堡",
                    "卢旺达", "罗马尼亚", "马达加斯加", "马耳他", "马尔代夫", "马拉维", "马来西亚", "马里", "马提尼克", "马约特", "毛里求斯", "毛利塔尼亚",
                    "美属维尔京群岛", "蒙古", "蒙特塞拉特", "孟加拉国", "秘鲁", "缅甸", "摩尔多瓦", "摩洛哥", "摩纳哥", "莫桑比克", "墨西哥", "纳米比亚", "南非",
                    "尼泊尔", "尼加拉瓜", "尼日尔", "尼日利亚", "挪威", "葡萄牙", "瑞典", "瑞士", "萨尔瓦多", "塞尔维亚", "塞拉利昂", "塞内加尔", "塞浦路斯",
                    "塞舌尔", "沙特阿拉伯", "圣巴泰勒米", "圣基茨和尼维斯", "圣卢西亚", "圣马丁", "圣马力诺", "圣皮埃尔和密克隆", "圣文森特和格林纳丁斯", "斯里兰卡",
                    "斯洛伐克", "斯洛文尼亚", "斯威士兰", "苏丹", "苏里南", "索马里", "泰国", "坦桑尼亚", "特克斯和凯科斯群岛", "特立尼达和多巴哥", "突尼斯", "土耳其",
                    "危地马拉", "委内瑞拉", "文莱", "乌干达", "乌克兰", "乌拉圭", "乌兹别克斯坦", "西班牙", "希腊", "新加坡", "新喀里多尼亚", "新西兰", "匈牙利",
                    "叙利亚", "牙买加", "亚美尼亚", "伊拉克", "伊朗", "以色列", "意大利", "印度", "印度尼西亚", "英国属地曼岛", "英属维尔京群岛", "约旦",
                    "越南", "赞比亚", "泽西岛", "乍得", "直布罗陀", "智利", "中非", "钻石公主号邮轮", "梵蒂冈", "斐济"
                ],
                provinceList: [
                    "安徽省", "澳门特别行政区", "北京市", "福建省", "甘肃省", "广东省", "广西壮族自治区", "贵州省", "海南省", "河北省", "河南省", "黑龙江省",
                    "湖北省", "湖南省", "吉林省", "江苏省", "江西省", "辽宁省", "内蒙古自治区", "宁夏回族自治区", "青海省", "山东省", "山西省", "陕西省",
                    "上海市", "四川省", "台湾省", "天津市", "西藏自治区", "香港特别行政区", "新疆维吾尔自治区", "云南省", "浙江省", "重庆市"
                ],
                countryArray: {
                    xAxis: [],
                    series: [
                        {
                            name: '确诊人数',
                            data: []
                        }, {
                            name: '治愈人数',
                            data: []
                        }, {
                            name: '死亡人数',
                            data: []
                        }
                    ]
                },
                avoidUpdate: false,
                currentChartName: "",
            }
        },
        watch: {
            chartType: function (type) {
                switch (type) {
                    case 'new':
                        this.chartData = this.newSerialData;
                        break;
                    case 'current':
                        this.chartData = this.remainData;
                        break;
                    case 'finish':
                        this.chartData = this.finishData;
                        break;
                    case 'total':
                        this.chartData = this.countrySerialData;
                        break;
                    default:
                        break;
                }
            },

            countrySelect: function (country) {
                this.updateCountry(country);
            },

            provinceSelect: function (province) {
                this.updateProvince(province);
            }
        },
        methods: {
            chinaConfigure(data) {
                let that = this;
                let myChart = echarts.init(this.$refs.myEchart);
                window.onresize = myChart.resize;
                myChart.setOption({
                    tooltip: {
                        show: false
                    }, // 鼠标移到图里面的浮动提示框
                    visualMap: {
                        show: false,
                        min: 0,
                        max: 5000,
                        text: ['High', 'Low'],
                        realtime: true,
                        calculable: true,
                        inRange: {
                            color: ['#5f7685', '#414f75', '#494775']
                        }
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
                                borderColor: '#303843'
                            },
                            emphasis: {
                                areaColor: null,
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowBlur: 0,
                                borderWidth: 0,
                                color: '#afcaed',
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
                            mapType: 'china',
                            geoIndex: 0,
                            data: data
                        }
                    ]
                });

                myChart.on('mouseover', function (params) {
                    api.getAreaData(params.name).then(
                        res => {
                            // console.log(res.confirmedCount);
                            params.current = res.currentConfirmedCount;
                            params.cured = res.curedCount;
                            params.value = res.confirmedCount;
                            that.currentData(params);
                        }
                    )

                });

                myChart.on('mouseout', function () {
                    that.currentData(that.country);
                });
            },
            currentData(param) {
                this.area = {
                    name: param.name,
                    current: isNaN(param.current) ? 0 : param.current,
                    cured: isNaN(param.cured) ? 0 : param.cured,
                    value: isNaN(param.value) ? 0 : param.value,
                };
            },
            getLocalData() {
                let storage = window.localStorage;
                this.statistic.totalDiagnosis = storage.getItem("confirmedCount");
                this.statistic.totalDiagnosisIncrement = storage.getItem("confirmedIncr");
                this.statistic.currentDiagnosis = storage.getItem("currentConfirmedCount");
                this.statistic.currentDiagnosisIncrement = storage.getItem("currentConfirmedIncr");
                this.statistic.currentSuspect = storage.getItem("suspectedCount");
                this.statistic.currentSuspectIncrement = storage.getItem("suspectedIncr");
                this.statistic.totalDeath = storage.getItem("deadCount");
                this.statistic.totalDeathIncrement = storage.getItem("deadIncr");
                this.statistic.totalHealing = storage.getItem("curedCount");
                this.statistic.totalHealingIncrement = storage.getItem("curedIncr");
                this.country.value = storage.getItem("confirmedCount");
                this.country.cured = storage.getItem("curedCount");
                this.country.current = storage.getItem("currentConfirmedCount");
                this.curTime = storage.getItem("date");

                this.area.value = storage.getItem("confirmedCount");
                this.area.cured = storage.getItem("curedCount");
                this.area.current = storage.getItem("currentConfirmedCount");
            },
            updateMap() {
                let myChart = echarts.init(this.$refs.myEchart);
                myChart.setOption({
                    series: [{
                        type: 'scatter',
                        coordinateSystem: 'geo' // 对应上方配置
                    },
                        {
                            name: '启动次数',
                            type: 'map',
                            geoIndex: 0,
                            data: this.provinceData
                        }
                    ]
                })
            },
            initAreaData() {
                //     console.log(this.provinceName.name);
                let that = this;
                for (let area of that.provinceName.name) {
                    // console.log(area);
                    api.getAreaData(area).then(
                        res => {
                            // console.log(res);
                            that.provinceData.push({
                                "name": area,
                                // 这里返回的是剩余的人数（去除治愈）
                                "value": res.confirmedCount,
                                "cured": res.curedCount,
                                "current": res.currentConfirmedCount,
                            });
                            console.log(that.provinceData.length)
                            if (that.provinceName.name.indexOf(area) === that.provinceName.name.length - 1) {
                                this.chinaConfigure(this.provinceData);
                            }
                        }
                    );
                }
            },
            initGlobalCountryData() {
                api.getCountrySerial('中国').then(
                    res => {
                        let chart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '确诊人数',
                                    data: []
                                }, {
                                    name: '治愈人数',
                                    data: []
                                }, {
                                    name: '死亡人数',
                                    data: []
                                }
                            ]
                        }
                        let newChart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '新增确诊人数',
                                    data: [],
                                }, {
                                    name: '新增疑似人数',
                                    data: [],
                                }
                            ]
                        }
                        let remainChart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '现存确诊人数',
                                    data: [],
                                }, {
                                    name: '现存疑似人数',
                                    data: [],
                                }
                            ]
                        }
                        let finishChart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '治愈人数',
                                    data: []
                                }, {
                                    name: '死亡人数',
                                    data: [],
                                }
                            ]
                        }
                        let preSuspect = 0;
                        let preConfirm = 0;
                        for (let tmp of res) {
                            chart.xAxis.push(tmp.date);
                            chart.series[0].data.push(tmp.confirmed);
                            chart.series[1].data.push(tmp.cured);
                            chart.series[2].data.push(tmp.dead);

                            // 往每日新增中增加数据
                            newChart.xAxis.push(tmp.date);
                            newChart.series[1].data.push(tmp.suspected - preSuspect);
                            newChart.series[0].data.push(tmp.confirmed - preConfirm);
                            preSuspect = tmp.suspected;
                            preConfirm = tmp.confirmed;

                            // 往现存数据中添加数据
                            remainChart.xAxis.push(tmp.date);
                            remainChart.series[0].data.push(tmp.confirmed - tmp.cured - tmp.dead);
                            remainChart.series[1].data.push(tmp.suspected);

                            // 往死亡/治愈数据中添加数据
                            finishChart.xAxis.push(tmp.date);
                            finishChart.series[0].data.push(tmp.cured);
                            finishChart.series[1].data.push(tmp.dead);
                        }
                        this.countrySerialData = chart;
                        this.newSerialData = newChart;
                        this.remainData = remainChart;
                        this.finishData = finishChart;


                        // 更新一下首页的状态
                        this.chartData = newChart;
                    }
                )
            },
            updateCountry(country) {
                if (country == "中国") {
                    if (this.avoidUpdate) {
                        this.avoidUpdate = false;
                        return;
                    }
                }
                this.currentChartName = country;
                api.getCountrySerial(country).then(
                    res => {
                        let chart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '确诊人数',
                                    data: []
                                }, {
                                    name: '治愈人数',
                                    data: []
                                }, {
                                    name: '死亡人数',
                                    data: []
                                }
                            ]
                        }
                        // 添加数据
                        for (let tmp of res) {
                            chart.xAxis.push(tmp.date);
                            chart.series[0].data.push(tmp.confirmed);
                            chart.series[1].data.push(tmp.cured);
                            chart.series[2].data.push(tmp.dead);
                        }
                        this.countryArray = chart;
                    }
                )
            },
            updateProvince(province) {
                this.currentChartName = province;

                this.avoidUpdate = true;
                this.countrySelect = "中国";

                api.getProvinceSerial(province).then(
                    res => {
                        let chart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '确诊人数',
                                    data: []
                                }, {
                                    name: '治愈人数',
                                    data: []
                                }, {
                                    name: '死亡人数',
                                    data: []
                                }
                            ]
                        }
                        // 添加数据
                        for (let tmp of res) {
                            chart.xAxis.push(tmp.date);
                            chart.series[0].data.push(tmp.confirmed);
                            chart.series[1].data.push(tmp.cured);
                            chart.series[2].data.push(tmp.dead);
                        }
                        this.countryArray = chart;
                    }
                )
            }

        },
        mounted() {
            this.getLocalData();
            this.initAreaData();
            this.initGlobalCountryData();
            // this.chinaConfigure();
            this.updateCountry('中国');
            // console.log()
        }
    }
</script>

<style scoped>
    .epidemic {
        z-index: 1;
        position: relative;
        margin-top: -8vh;
    }

    .map-wrapper {
        height: 100vh;
        width: 100%;
    }

    .content-wrapper {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
    }

    .data-wrapper {
        text-align: left;
        position: absolute;
        top: 17vh;
    }

    .area-wrapper {
        text-align: right;
        position: absolute;
        /* top: 72vh; */
        top: 48vh;
        right: 0;
    }

    .divider-wrapper {
        margin: 0 30px;
    }

    .select-wrapper {
        padding-left: 80px;
    }

    .tables-wrapper {
        text-align: left;
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
