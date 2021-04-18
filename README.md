![workflow](https://github.com/inuyVessalius/mymori-api/actions/workflows/gradle.yml/badge.svg)
# Mymori
Mymori is a simple memory game

# Endpoints

* (POST) /createUser - Creates an user - working
* (GET) /getUserByName/{name} - Gets an user by it's first name - working
* (GET) /getUser/{id} - Gets an user by it's id - working
* (DELETE) /deleteUser/{id} - Deletes an user by it's id - working
* (POST) /createGame - Creates a game - working
* (GET) /getGameByUserId - Gets a game by the player's id - working
* (DELETE) /deleteGame/{id} - Deletes a game by it's id - working
* (POST) /createCard - Creates a card - working
* (GET) /getCard/{id} - Gets a card by it's id - working
* (DELETE) /deleteCard/{id} - Deletes a card by it's id - working
* (POST) /createCards - Creates a list of cards - working
* (GET) /getAllCards - Gets a list of cards - working
* (DELETE) /deleteCards - Deletes a list of cards - working

# User Stories

* I as a player want to create an account
* I as a player want to play a game
* I as a player want to add cards to my game
* I as a player want to see the games I've played 
