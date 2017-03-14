# Fonctionnalités implémentées 14/03/2017

Cette implémentation suit les règles suivantes :

  * la partie gestion du site n'est pas implémentée;
  * il faut au minimum 4 joueurs pour commencer une partie;
  * une manche de *Poker* se déroule via les enchères suivant : *Blind*, *PreFlop*, *Flop*, *Turn* et *River*;
  * lors de la *Blind*
    * aucune carte n'est distribuée;
    * le premier joueur doit miser la *SmallBlind* qui est définie à 1;
    * le joueur suivant, la *BigBlind*, doit miser le double de cette mise;
  * lors de la *PreFlop*
    * deux cartes sont distribuées à chaque joueur;
    * le premier joueur à parier est celui qui suit la *BigBlind*;
  * lors de la *Flop*
    * 3 cartes communes sont distribuées sur le tableau;
    * le premier joueur à parier est celui qui suit la *BigBlind*;
  * lors des enchères *Turn* et *River*
    * 1 carte commune est distribuée sur le tableau;
    * le premier joueur à parier est celui qui suit la *BigBlind*;
  * lors des enchères *PreFlop*, *Flop*, *Turn* et *River*, un joueur peut :
    * suivre (to call) : le joueur dépose le montant de la mise en cours;
    * relancer  (to raise) : le joueur mise plus que le montant du pari en cours, attention, après une relance, un nouveau tour complet des joueurs est effectué;
    * se coucher (to fold) : le joueur abandonne la manche;
    * faire tapis (allIn) : si le joueur ne possède pas assez d'argent pour suivre mais qu'il souhaite participer à la manche, il peut déposer l’entièreté de son argent, il ne gagnera par contre qu'une proportion du pot en jeu;
    * parler (to check) : un joueur peut, sous certaines conditions, passer son tour. Ce pari n'est pas implémenté
  * en fin de *River*, un joueur a une main unique composée de 5 cartes, 2 qu'il possède depuis le début de la manche, et 3 qu'il choisit parmis les 5 du tableau;
  * si un joueur fait tapis, un pot parallèle est créé;
  * si à la fin d'un pari il reste un unique joueur, ce joueur gagne le pot
  * dans le cas contraire, les cartes des joueurs sont comparées et le pot est distribué entre les vainqueurs.

# Fonctionnalités non implémentées  17/03/2017

* Parler (ou faire « parole »)

# Liens de documentations

  * [Principe du jeu](https://fr.wikipedia.org/wiki/Poker)

  * [Liste des enchères](https://fr.wikipedia.org/wiki/Enchère_%28poker%29)
  * [Tutoriel sur les enchères](http://www.poker-training-academy.com/cours-de-poker-encheres-au-poker_pageid76.html)  

  * [Liste des mains](https://fr.wikipedia.org/wiki/Main_au_poker)

  * [Pot parallèle en cas de tapis]( https://fr.wikipedia.org/wiki/Pot_parallèle_%28poker%29)

  * [Dynamique du jeu]( http://www.poker-training-academy.com/poker-tutorial-coup-simple-poker_pageid77.html)

  * [Fichier Markdown (.md)](https://fr.wikipedia.org/wiki/Markdown)

# Mise à jour effectuées  17/04/2017
>A compléter

# Mise à jour effectuées  08/05/2017
>A compléter
