<template>
  <el-container id="main-content" class="clearfix">
    <el-header>
      <div class="page-header position-relative">
        <h1 style="color: #2679b5;">
          借卖方
          <small>
            <i class="icon-double-angle-right"></i> 我的信息
          </small>
        </h1>
      </div>
    </el-header>
    <el-main>
      <el-main class="inputName">
        <div style="margin-bottom: 40px">Username:gmcbrand1</div>
        <div>
          <label class="green">Name:</label>
          <input type="text" value="Lucy" />
        </div>
        <div>
          <label class="green">Email:</label>
          <input type="text" value="97656444@123.com" />
        </div>
        <div>
          <label class="green">Phone:</label>
          <input type="text" value="1376533621" />
        </div>
        <el-button type="info" @click="save()">save</el-button>
        <el-button type="info" @lick="myshop()">My Stores</el-button>
      </el-main>
    </el-main>

  </el-container>
</template>

<script>
    export default{
        
        methods:{
            save(){

            },
            myshop(){
                
            }
        }       
    }

</script>

<style>
    .main-content {
        border:1px solid red;
        padding-left: 7px;
        padding-right: 7px;
    }
    .el-header {
        margin: 0 0 12px;
        border-bottom: 1px dotted #e2e2e2;
    }
    .Register {
        width: 50%;
        margin: auto;
    }
    .green {
        color: #69aa46!important;
    }
    .btn-success {
        background-color: #87b87f!important;
        border-color: #87b87f;
    }
    .btn {
        display: inline-block;
        padding: 0 12px 1px;
        line-height: 32px;
        color: #FFF!important;
        text-shadow: 0 -1px 0 rgba(0,0,0,0.25)!important;
        font-size: 14px;
        background-image: none!important;
        background-color: #abbac3!important;
        border: 5px solid #abbac3;
        border-radius: 0!important;
        box-shadow: none!important;
        -moz-transition: all .1s ease-in-out;
        -webkit-transition: all .1s ease-in-out;
        -o-transition: all .1s ease-in-out;
        -ms-transition: all .1s ease-in-out;
        transition: all .1s ease-in-out;
        cursor: pointer;
        vertical-align: middle;
        margin: 0;
        position: relative;
    }
    .div{
        margin: 0;
        font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
        font-size: 14px;
        line-height: 20px;
        color: #333;
        background-color: #fff;

    }
</style>
