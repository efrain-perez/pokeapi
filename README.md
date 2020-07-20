# PokeAPI 
This application is a REST API intended to give additional information about Pokemon battles and common moves among different Pokemons.

It is not intended to replace the original [PokeAPI](https://pokeapi.co) but instead  extend its capabilities. This application gets all the information needed from that API.

## API usage
The API is easy to use and does not need any authentication method and is available at [pokeapi.efrain.dev](https://pokeapi.efrain.dev). For a more interactive documentation, you can visit its [swagger page](https://pokeapi.efrain.dev/swagger-ui.html). The two main (and only) endpoints are:

### /battle
```
GET -> /battle?firstPokemon={ANY-POKEMON}&secondPokemon={ANOTHER_POKEMON}
```
This endpoint provides information about a battle between _firstPokemon_ and _secondPokemon_, such as the types of each pokemon and the multiplier when attacking. 

**Parameters:**
- _firstPokemon_ (required): ID or name for the first pokemon.
- _secondPokemon_ (required): ID or name for the second pokemon.

### /moves/shared
```
GET -> /moves/shared?pokemons={LIST-OF-POKEMON}[&language={PREFERRED_LANGUAGE}&size={PAGE_SIZE}&offset={OFFSET}]
```
Gets a list of all the shared moves for all the pokemons you want in the language you want (as long as it is supported). You can also limit the numbers of results and/or begin at any point of the list. Moves are sorted by its ID.

**Parameters:**
- _pokemons_ (required): List of IDs or names for the pokemons separated by a comma.
- _language_ (optional): ID or short name for the desired language. Default is 'en'.
- _size_ (optional): Maximum size of the list. Default is 10.
- _offset_ (optional): Starting point or number of moves to skip. Default is 0.

# Local Set Up
## Running as Standalone
If all you want is to run the application locally, you can do it by downloading the repository and running this command:
```
./mvnw spring-boot:run
```
This will start a server on port `8080` and you can access going to `localhost:8080` on any browser.

## Running with Minikube
To run our application on a Kubernetes cluster, we're going to user Minikube. Follow [this guide](https://kubernetes.io/docs/tasks/tools/install-minikube/) if you don't have it. Aditionally we're going to need the ingress plugin, which can be enabled by running this command: 
```
minikube addons enable ingress
```
With minikube up and running we can set up our application executing these commands (one by one):
```
kubectl apply -f deployment/pokeapi-deployment-local.yml
kubectl apply -f deployment/pokeapi-service-local.yml
kubectl apply -f deployment/pokeapi-ingress-local.yml
```
To verify each compoment you can execute 
```
kubectl get deployment
kubectl get service
kubectl get ingress
```
Each of these should return one element. 

To access our application you will need the address indicated on the ingress result, if the column is empty, you have to wait a few minutes to get a result.

Once you have the address, modify the file `/etc/hosts` and add this at the end:
```
{INGRESS_ADDRESS} local-pokeapi.info
```

After this, go to local-pokeapi.info in your browser.

### Making changes
To get changes in your local minikube instance, you have to push your code and make a new image using our [Jenkins instance](https://jenkins.efrain.dev).

To make this click on the "_PokeAPI build and publish_" job, select your branch and click on "_Build with Parameters_". On the next page it will ask for a version for the new image, input the desired version and click _Build_. 

If the version of your new image is different than the one you have on pokeapi-deployement.yml, first you have to edit it on the following line: 
```
image: efrainperez/pokeapi:{THE_NEW_VERSION}
```
And then run the following:
```
kubectl apply -f deployment/pokeapi-deployment.yml
```

If the version didn't change you can run the following:
```
kubectl scale --replicas=0 deployment pokeapi-deployment --namespace=pokeapi-prod
kubectl scale --replicas=1 deployment pokeapi-deployment --namespace=pokeapi-prod
```
This will make minikube to stop the current deployment and run it again, making it to pull the new image.
