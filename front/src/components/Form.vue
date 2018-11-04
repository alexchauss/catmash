<template>
  <div class="form">
    <h1>Welcome to catmash!</h1>
    <h2>Vote for the cutest kitten</h2>
    <ul id="cats">

    </ul>
  </div>
</template>

<script>
export default {
  	name: 'Form',
	data: {
	    cats: [] // initialize empty array
	  },
	  mounted() { // when the Vue app is booted up, this is run automatically.
	    var self = this; // create a closure to access component in the callback below
	    $.getJSON('https://latelier.co/data/cats.json', function(data) {
	      self.cats = data;
	    });

	    for(key in Object.keys(cats))
			$("#cats").append("<li>" + JSON.stringify(key) + "</li>");

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
