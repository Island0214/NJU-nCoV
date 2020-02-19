<template>
    <div class="navigation-wrapper">
        <el-header>
            <el-row>
                <el-col :span="8" style="text-align: left">
                    <p><strong>NJU - nCoV</strong></p>
                </el-col>
                <el-col :span="16" style="text-align: right">
                    <router-link to="/"><button>首页</button></router-link>
                    <router-link to="/epidemic"><button>实时疫情</button></router-link>
                    <router-link to="/"><button>信息资讯</button></router-link>
                        <router-link to="/"><button>SARS</button></router-link>
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
    }

    button {
        line-height: 60px;
    }
</style>