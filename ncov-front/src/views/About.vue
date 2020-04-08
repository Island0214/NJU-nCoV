<template>
  <div class="about-us">
    <div class="person">
      <div class="left">
        <div class="image-left"><img src="../assets/孙玲.jpg" width="80" height="80"></div>
        <div class="name">孙玲</div>
      </div>
      <div class="info info-left">
          <div class="self-top">一</div>
          <div class="self-bottom">一</div>
      </div>
    </div>
    <div class="person">
      <div class="info info-right">
        <div class="self-top">一些介绍一些介绍一些介绍一些介绍一些介绍一些介绍一些介绍一些介绍一些介绍一些介绍一些介绍</div>
        <div class="self-bottom">一些介绍</div>
      </div>
      <div class="left">
          <div class="image-right"><img src="../assets/钱柯宇.jpg" width="80" height="80"></div>
          <div class="name">钱柯宇</div>
        </div>
    </div>
    <div class="person">
      <div class="left">
        <div class="image-left"><img src="../assets/唐鑫.jpg" width="80" height="80"></div>
        <div class="name">唐鑫</div>
      </div>
      <div class="info info-left">
          <div class="self-top">一些介绍一些介绍一些介绍一些介绍一些介绍一</div>
          <div class="self-bottom">https://github.com/Angelica-dahurica</div>
      </div>
    </div>
    <div class="person">
      <div class="info info-right">
          <div class="self-top">
            <p>南京大学软件学院</p>
            <p>https://github.com/IDmeforever/</p>
          </div>
          <div class="self-bottom">idmeforever at Gmail.com</div>
      </div>
      <div class="left">
        <div class="image-right"><img src="../assets/张子昂.jpg" width="80" height="80"></div>
        <div class="name">张子昂</div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">

</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  .about-us
    width: 960px
    margin: 0 auto
    font-size: 0
    padding-top 200px
    .person
      display: inline-block
      border-radius: 5px
      width: 460px
      height: 130px
      margin: 0 10px 20px 10px
      .left
        vertical-align: top
        display: inline-block
        width: 90px
      .image-left
        display: inline-block
        width: 80px
        margin: 20px 10px 20px 20px
        border-radius: 40px
        overflow: hidden
      .image-right
        display: inline-block
        text-align: right
        width: 80px
        margin: 20px 20px 20px 20px
        border-radius: 40px
        overflow: hidden
      .name
        display: inline-block
        width: 120px
        text-align: center
        font-size: 14px
      .info
        display: inline-block
        width: 370px
      .info-left
          text-align left
      .info-right
          text-align right
        .self-top
          width: 330px
          display: inline-block
          margin: 10px 10px 10px 30px
          font-size: 14px
          line-height: 150%
        .self-bottom
          width: 330px
          display: inline-block
          margin: 0 10px 10px 30px
          font-size: 14px
          line-height: 150%
</style>
