<template>
	<div class="scores">
	    <h1>Who's the cutest for now?</h1>
	    <ul id="results"/>
  </div>
</template>

<script>
	import $ from 'jquery'
	import kitties from '../assets/data/cats.json'
	export default {
	  	name: 'Scores',
		mounted() {

			//	création d'un objet cats qui contient tous les chats importés du json

			var cats = new Object();
			for(let i = 0; i < kitties["images"].length; i++)
				cats[kitties["images"][i].id] = [kitties["images"][i].url,0];

			//	récupération des votes du back

			$.ajax({
			        type: "GET",
			        url: '/vote',
			        dataType: 'json',
			        contentType: "application/json",
			        success: function (response) {

			        	response.forEach(function(elt) {
							cats[elt["catId"]][1]++		// ajout d'un vote pour ce chat
			        	})

			        	// tri des chats par nb de votes décroissant

			        	var keys = Object.keys(cats);

						var max = 0;

						for(var i = 0; i < keys.length; i++)
			        		if(cats[keys[i]][1] > max)
			        			max = cats[keys[i]][1];

						var sortedIdByVotes = [];

						while(max >= 0)
						{
							for(var i = 0; i < keys.length; i++)
				        		if(cats[keys[i]][1] == max)
				        			sortedIdByVotes.push(keys[i]);

			        		max--;
						}

						//	affichage des chats triés par score décroissant

						var compteur = 0;
						var lastScore = -1;

			        	for(var i = 0; i < sortedIdByVotes.length; i++)
			        	{

			        		var key = sortedIdByVotes[i];
			        		var medalHtml = "";

			        		if(cats[key][1] != lastScore)
			        			compteur++;

			        		lastScore = cats[key][1];

			        		if(compteur == 1)
			        			medalHtml = " - WINNER!!!";
			        		else if(compteur == 2)
			        			medalHtml = " - Second score, waw that's a cat ^^";
			        		else if(compteur == 3)
			        			medalHtml = " - Third score, well done kitty";

			        		$("#results").append("<li><img src='" + cats[key][0] +
			        		"' style='cursor:pointer;max-width:140px;margin:10px' title='" +
			        		key + "'/><span style='display:block'>" +
			        		cats[key][1] + " vote" + (cats[key][1]>1?"s":"") + medalHtml + "</span></li>");
			        	}

			        },
			        complete: function () {
						$(window).scrollTop(0);
			        }
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
