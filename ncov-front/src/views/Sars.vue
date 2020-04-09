<template>
    <div class = "epidemic">
        <div>
            <div class="tables-wrapper">
                <el-row>
                    <el-col :span="6">
                        <div class="select-wrapper">
                            <h1>SARS疫情发展推移<br>——</h1>
                            <el-radio v-model="chartType" label="new">2003年SARS每日数据</el-radio>
                        </div>
                    </el-col>
                    <el-col :span="18">
                        <StackAreaChart :data="chartData" v-if="chartType === 'total'"></StackAreaChart>
                        <LineChart :data="chartData" v-else></LineChart>
                    </el-col>
                </el-row>
            </div>
        </div>

        <el-divider></el-divider>

        <div>
            <h1>什么是SARS？</h1>
            <p class="textcss" style="font-size: 14px; font-weight: 500; line-height: 16px;">
                引自wiki：<a target="_blank" href="https://zh.wikipedia.org/wiki/嚴重急性呼吸系統綜合症">严重急性呼吸道综合征</a>
            </p>
            <img src="https://upload.wikimedia.org/wikipedia/commons/c/cf/SARS_virion.gif" alt="SARS病毒颗粒的电子显微镜摄影图" width="200px"/>
            <p class="textcss">
                严重急性呼吸道综合征（英语：Severe Acute Respiratory Syndrome，缩写为SARS），是非典型肺炎的一种。2002年，该病在中华人民共和国广东顺德首发，并扩散至东南亚乃至全球，称为SARS事件。
            </p>
            <p class="textcss">
                严重急性呼吸道综合征由世界卫生组织的意大利医生卡尔娄·武尔班尼发现。他在越南研究该病毒时受到感染，其后于2003年3月在泰国不治殉职。研究指出，该病是由严重急性呼吸道综合征冠状病毒引起的，传播途径包括由已被感染者所咳出的飞沫传染，而整个传染过程主要透过人与人近距离的接触。此病平均导致7%至15%的患者死亡。世界卫生组织警告，该病死亡率在未来有可能达到15%。不同年龄段中，24岁和以下人群死亡率为1%，25到44岁死亡率为6%，45到64岁死亡率15%，65岁以上的死亡率则为55%。
            </p>
            <p class="textcss">
                “SARS”一词在亚洲各地有不同习惯称呼。大陆惯称为“非典型肺炎”，并简称“非典”，但两者并非同义词，SARS为非典型肺炎的一种。香港习惯把“SARS”依香港粤语译为“沙士”，或直接用英文“SARS”。台湾亦惯用英语SARS。马来西亚华文媒体称为SARS。新加坡媒体一度使用“薩斯”，4月17日，新加坡中文传媒统一译名委员会统称为“沙斯”。
            </p>
            <h2>病毒的发现</h2>
            <p class="textcss">
                2003年2月7日开始，中国疾病预防控制中心病毒学首席研究员、中国工程院院士洪涛开始进行对非典型肺炎病毒的研究，于电子显微镜下发现病人肺部存在衣原体，从而初步认定其为致病原因，2月18日下午向社会公布了自己的研究成果。该研究成果获得中国政府官方认可，2月18日当晚权威媒体发布“引起广东部分地区非典型肺炎的病原基本确定为衣原体。”的新闻。但由于抗衣原体药物完全无效，身在一线的广东医疗界人士并不认可该结论，广州医学院的钟南山院士在紧急会议上表示认定病原为衣原体科学依据不足，不能以此为根据制定治疗方案。2月底，军事医学科学院微生物传染病研究所祝庆余、秦鄂德两位研究员一例尸解标本中分离辨认出冠状病毒，但因为仅限于形态学上的证明，结论并未上报。
            </p>
            <p class="textcss">
                3月15日世界卫生组织正式将该病定名为严重急性呼吸系统综合症（SARS），向全球发布了旅行警告。随后，世界卫生组织的斯托尔博士迅速动员了12个实验室组建实验室网络，并在开始阶段表示希望各实验室把注意力集中在副粘液病毒上。香港中文大学许树昌教授和香港威尔士亲王医院微生物系的研究者、德国法兰克福大学医学病毒研究所的研究人员则利用电子显微镜，找到了有结构像副黏液病毒的病毒颗粒，认定SARS病毒属于副黏液性病毒科，香港威尔士亲王医院于3月18日宣布该结论，3月19日再经香港卫生福利及食物局局长杨永强医生向外公布。同时台湾卫生署官员表示，台湾各检验单位早已倾向是副黏液病毒，但未敢确定。但不久后，通过对3月上旬收治的病人身上所获取的样本进行分析，以佩里斯博士和袁国勇博士为领导的香港大学实验室在玛丽医院的实验室进行的试验否定了该结论，以拉里·安德森为领导的美国疾病预防与控制中心经过实验也得到同样结论，同时两组实验人员均发现了具有冠状病毒形态的嫌疑病毒。但由于已知的冠状病毒不导致严重的人类疾病，只会引起中度或轻度的感冒，因此学界对此还存在疑问，3月21日，美国疾病预防与控制中心将病毒RNA样本送往旧金山加州大学约瑟夫·德里西博士的实验室，以通过其新发明的基因芯片法进行检测，3月22日，该嫌疑病毒被进一步确认为冠状病毒。3月21日，祝庆余、秦鄂德两位研究员也完成了对病毒在血清学、免疫学、分子生物等方面的研究。
            </p>
            <p class="textcss">
                3月31日，洪涛表示：“我们不敢排除有其他的原因，比如说衣原体和冠状病毒同时作用，但衣原体绝对是主要元凶。”4月4日，中国疾控中心召开的记者座谈会上，中国疾控中心主任李立明仍然主要介绍了关于衣原体的研究进展，对于记者关于海外有关冠状病毒的研究的提问，李立明表示疾控中心同样找到了三例冠状病毒，其序列片断和世界卫生组织及香港公布相同，“但这不一定证明一定是病原”。
            </p>
            <p class="textcss">
                4月5日，加拿大不列颠哥伦比亚大学癌症研究所的研究人员收到加拿大国家微生物实验室所寄来的病毒RNA样本，开始展开研究工作，由分子生物学家贾斯温德尔·哈特拉领导进行反转录工作，霍尔特医生则进行基因组排列分析仪的准备工作，研究人员最终于4月12日凌晨2时25分得到该病毒的基因组图，并随后发布。4月14日，亚特兰大的安德森医生领导的研究小组同样绘制出了基因组图，经不列颠哥伦比亚大学比较发现两组基因组图相同。祝庆余、秦鄂德两位研究员自3月下旬以来的研究结论经过逐级上报，最终于4月11日公布。世界卫生组织引述了相关发现后表示“中国内地发现的SARS与香港的SARS可能具有同源性”。 2003年4月16日，世界卫生组织负责传染病的执行干事戴维·海曼正式宣布冠状病毒的一个变种是引起非典型肺炎的病原体，在多个疾病控制实验室发现的冠状病毒确实是引起SARS的病毒.4月17日，世界卫生组织称荷兰埃拉斯默斯大学的艾伯特·奥斯特豪斯带领的研究小组已经成功地使猴子感染上这种冠状病毒并再次分离出来，这表明四条科赫法则均已被满足。
            </p>
            <h2>SARS症状</h2>
            <p class="textcss">“疑似病例”是定义为在2003年2月1日之后有以下病状历史的人：</p>
            <div class="ul-wrapper">
                <ul><li>发高烧高于38℃（100.4℉）以及一个或是更多个下列的呼吸症状：
                <ul><li>头晕症状</li>
                <li>呼吸急促</li>
                <li>呼吸困难</li>
                <li>低氧症</li>
                <li>肺炎</li>
                <li>咳嗽</li></ul></li>
                <li>除了发高烧和一个呼吸症状之外，还要一个或是更多个以下的情况：
                <ul><li>与疑似病人亲密接触（例如住在一起）</li>
                <li>最近曾经到访严重急性呼吸道综合征感染地区旅游</li></ul></li></ul>
            </div>
            <p class="textcss">除了发烧和呼吸症状，其它跟严重急性呼吸道综合征有关的症状包括：</p>
            <div class="ul-wrapper">
                <ul><li>头痛</li>
                <li>肌肉僵硬</li>
                <li>失去食欲</li>
                <li>全身虚弱</li>
                <li>精神紊乱</li>
                <li>出疹</li>
                <li>腹泻</li>
                <li>白血球和血小板数目通常会变低</li>
                <li>发冷</li>
                <li>发颤</li></ul>
            </div>
        </div>

        <el-divider></el-divider>

        <div>  
            <h1>SARS病毒与COVID-19病毒的对比</h1>
            <p class="textcss">
                严重急性呼吸系统综合征冠状病毒2（英语：Severe acute respiratory syndrome coronavirus 2，缩写：SARS-CoV-2）是冠状病毒科乙型冠状病毒属严重急性呼吸道综合征相关冠状病毒种的一个分型，具有包膜的正链单股RNA，它造成了于2019年底爆发的2019冠状病毒病（COVID-19）。它的基因序列和SARS病毒及MERS病毒属于同一谱系但不同进化枝。
            </p>
            <p class="textcss">
                2020年1月中旬在疫情爆发期间，通过对一位阳性患者样本进行核酸检测以及基因组测序后发现了这一病毒，目前野外来源与中间宿主尚不明确，人类受病毒感染的患者目前没有独特的临床症状，多数症状为低烧、无力与口鼻症状、干咳、部分伴随肠胃不适。迄今为止，没有任何疫苗被批准投入使用，但在临床治疗中，有多种病毒抑制药物被采纳。
            </p>
            <p class="textcss">
                2019年12月开始，这一病毒在中华人民共和国湖北省武汉市首次发现，四个月后扩散范围超过200个国家，截至2020年4月5日，全球约有120万人确诊感染，其中死亡人数逾6万人，目前数字仍快速攀升中。美国、意大利、西班牙、德国、法国确诊人数已超越中国，但意大利、西班牙、美国、法国、英国、伊朗死亡人数已超越中国。
            </p>
            <h2>【TED】新型冠状病毒的现状及未来发展趋势</h2>
            <iframe src="//player.bilibili.com/player.html?aid=98773504&bvid=BV177411U7V8&cid=171527426&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"  width=800px height=600px> </iframe>
        </div>
        <Footer> </Footer>
    </div>
</template>

<script>
    import {api} from "../request/api";
    // import echarts from "echarts"
    import LineChart from '../components/charts/LineChart.vue'
    import Footer from '../components/Footer.vue'
    import StackAreaChart from '../components/charts/StackAreaChart.vue'


    export default {
        name: "Sars",
        components: {
            LineChart,
            StackAreaChart,
            Footer
        },
            
        data() {
            return {
                chartData: {
                    xAxis: [],
                    series: [{
                        name: '确诊人数',
                        data: []
                    }, {
                        name: '疑似人数',
                        data: []
                    }, {
                        name: '死亡人数',
                        data: []
                    }]
                },
                chartType: 'new',
            }
        },
        watch: {
            // chartType: function(type) {
            //     switch(type) {
            //         case 'new':
            //             this.chartData = this.chartData;
            //             break;
            //         default:
            //             break;
            //     }
            // }
        },
        methods: {
            loadAllSarsData() {
                api.getAllSarsData('China').then (
                    res => {
                        let chart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '确诊人数',
                                    data: []
                                }, {
                                    name: '死亡人数',
                                    data: []
                                }, {
                                    name: '疑似人数',
                                    data: []
                                }
                            ]
                        }
                        for(let tmp of res) {
                            chart.xAxis.push(tmp.date);
                            chart.series[0].data.push(tmp.confirm);
                            chart.series[1].data.push(tmp.dead);
                            chart.series[2].data.push(tmp.suspect);
                        }
                        this.chartData = chart;
                    }
                )
            }
        },
        mounted() {
            this.loadAllSarsData();
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

    .textcss{
        text-align: left !important;
        margin: 1em 60px 1em 60px;
        font-size: 16px;
        text-indent: 32px;
    }

    .ul-wrapper {
        text-align: left;
        margin: 1em 60px 1em 60px;
        color: #cfcfcf;
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
