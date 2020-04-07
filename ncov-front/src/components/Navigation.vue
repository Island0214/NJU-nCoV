<template>
    <div class="navigation-wrapper">
        <el-header>
            <el-row>
                <el-col :span="8" style="text-align: left">
                    <p><strong>NJU - nCoV</strong></p>
                </el-col>
                <el-col :span="16" style="text-align: right">
                    <router-link to="/"><button>首页</button></router-link>
                    <router-link to="/epidemic"><button>国内疫情</button></router-link>
                    <router-link to="/worldEpidemic"><button>世界疫情</button></router-link>
                    <router-link to="/news"><button>信息资讯</button></router-link>
                    <router-link to="/sars"><button>SARS</button></router-link>
                    <router-link to="/about"><button>关于我们</button></router-link>
                </el-col>
            </el-row>

        </el-header>
    </div>
</template>

<script>
    export default {
        name: "Navigation"
    }
</script>

<style scoped>
    .navigation-wrapper {
        height: 60px;
        position: relative;
        z-index: 2;
    }

    button {
        line-height: 60px;
    }

    p {
        height: 60px;
        line-height: 60px;
    }
</style>