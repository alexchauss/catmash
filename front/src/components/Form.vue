<template>
  <div class="form">
    <h1>Welcome to catmash!</h1>
    <h2 id="msg">Vote for the cutest kitten</h2>
    <a href="vote" id="votesLink" style="visibility:hidden">See the scores</a>
    <ul id="cats">

    </ul>
  </div>
  <notify-me
        :event-bus="bus">
      <template slot="content" scope="{data}">
          <div style="width: 100%; word-break: break-all; text-align: left">
              <h4><b>{{data.title}}</b></h4>
              <p style="margin: 0">{{data.text}}</p>
          </div>
      </template>
  </notify-me>
</template>

<script> import $ from 'jquery' </script>
<script>
import cats from '../assets/data/cats.json'
import Vue from 'vue'
export default {
  	name: 'Form',
	  mounted() {
	    var self = this;


		let userEmail = "mail@test.br";

	    for(let i = 0; i < cats["images"].length; i++)
			$("#cats").append("<li><form class='formCat' action='vote' enctype='application/json'><button type='submit' style='" +
			"padding: 0px;border: none;box-sizing: unset;color: transparent;  background-color: transparent" +
			"'><img src='" + cats["images"][i].url +
			"' style='cursor:pointer;max-width:140px;margin:10px' title='click to vote for me ^^'></a>" +
			"<input type='hidden' name='catId' value='" + cats["images"][i].id +
			"'/><input type='hidden' name='email' value='" + userEmail + "'/></form></li>");

		$(".formCat").submit(function (e) {
	    e.preventDefault();
	    var data = $(this).serializeFormJSON();
	    var dataJson = JSON.stringify(data).split("catId").join("\"catId\"").split("email").join("\"email\"");
		dataJson = dataJson.split("\"\"").join("\"");
	    console.log(dataJson);

	    $.ajax
		    ({
		        type: "POST",
		        //the url where you want to sent the userName and password to
		        url: 'vote',
		        dataType: 'json',
		        contentType: "application/json",
		        //json object to sent to the authentication url
		        data: dataJson,
		        complete: function () {
					$("#msg").html("Thanx for voting :)");
					$("#votesLink").css("visibility","visible");
					$(window).scrollTop(0);
		        }
		    })
		});

		$.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

  	}

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
