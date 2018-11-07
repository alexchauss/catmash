<template>
  <div class="cats">
    <h1>Welcome to catmash!</h1>
    <h2 id="msg">Vote for the cutest kitten</h2>
    <a id="linkToScores" style="visibility:hidden;text-decoration:underline;color:blue;cursor:pointer">See the scores</a>
    <ul id="cats"/>
  </div>
</template>

<script>
	import $ from 'jquery'
	import kitties from '../assets/data/cats.json'
	import Vue from 'vue'
	export default {
  		name: 'Cats',
	  	mounted() {
		    var self = this;

			let userEmail = "mail@test.br";

		    for(let i = 0; i < kitties["images"].length; i++)
				$("#cats").append("<li><form class='formCat' action='vote' enctype='application/json'><button type='submit' style='" +
				"padding: 0px;border: none;box-sizing: unset;color: transparent;  background-color: transparent" +
				"'><img src='" + kitties["images"][i].url +
				"' style='cursor:pointer;max-width:140px;margin:10px' title='click to vote for me ^^'></a>" +
				"<input type='hidden' name='catId' value='" + kitties["images"][i].id +
				"'/><input type='hidden' name='email' value='" + userEmail + "'/></form></li>");

			// si clique sur un chat (vote)

			$(".formCat").submit(function (e) {
			    e.preventDefault();
			    var data = $(this).serializeFormJSON();
			    var dataJson = JSON.stringify(data).split("catId").join("\"catId\"").split("email").join("\"email\"");
				dataJson = dataJson.split("\"\"").join("\"");
			    console.log(dataJson);

				//	envoi au module back du vote par une requête POST en ajax

			    $.ajax({
			        type: "POST",
			        url: 'vote',
			        dataType: 'json',
			        contentType: "application/json",
			        data: dataJson,
			        complete: function () {		//	affichage d'un message + du lien vers les scores
						$("#msg").html("Thanx for voting :)");
						$("#linkToScores").css("visibility","visible");
						$(window).scrollTop(0);
			        }
				})
			});

			//	fonction pour jsonifier le form

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

			//	si clic sur lien, routage vers les scores (autre composant front Vue.js)

		    $("#linkToScores").click(function() {
		    	self.$router.push("scores")
		    })

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
