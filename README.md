#REPONSES AUX QUESTIONS DU TP3 :

Q1 :
    Que se passe-t-il pour des plateaux de toute petite taille (2,3,4)?
        Oui, situation abérrante, le second joueur gagnera à coup sûr s'il pose une reine à son premier tour
        puisque le joueur 1 doit poser un cailloux.

    Le rôle du premier joueur détermine-t-il alors le vainqueur ? Comment ?
        Le rôle du premier joueur determine clairement l'issue de la partie à mesure que le tableau est petit,
        plus il est grand moins c'est vrai. A cause du fait de la couverture de la première reine posée qui empecherait
        le second joueur d'en poser une aussi

Q2 : La règle qui force le premier joueur à jouer un rocher au premier coup semble-t-elle juste ?
        La règle de forcer le premier joueur est plutôt juste puisque cela empeche le joueur 1 de poser une reine qui couvrerait
        un maximum de cases mais plutôt de réfléchir à ces prochains coups et donc de faire un premier coup plutôt defensif.

     Cela influe-t-il sur les scores ?
        Encore une fois cette question dépend de la taille du plateau mais plus le plateau est grand moins cela est vrai

Q3 : Les symétries sont-elles importantes ici ?

     Est-ce qu'une partie sur un plateau de taille impaire est la même que sur un plateau de taille paire ?

Q4 : Les valeurs relatives d'un rocher et d'une reine importent-elles ?
        Elles sont en effet importantes puisqu'elles contribuent en grande partie à la priorisation de jouer une pièce
        ou l'autre.

     Que se passe-t-il si un rocher vaut plus qu'une reine ?
        Il faudra quand même rester dans l'optique de placer un maximum de reines etant donné que l'on à un maximum de rochers
        possibles à poser. (Pour un taille de board suffisante)

     Si les deux valent autant ?
        Il faudra aussi prioriser la pose de reines puis de rocks par la suite à cause de la limite du nombre de rocks
        possibles.

     Si la valeur de la reine est supérieure, mais pas de beaucoup ?
        Même chose que au dessus.

     Si un rocher ne vaut rien ?
        Même chose que au dessus.

     Si un rocher a une valeur négative ?
        Même chose que au dessus, mais calculer si c'est plus avantageux de perdre au tour là les points pour pouvoir
        en gagner plus ensuite en débloquant des cases.

Q5 : En dehors de tout contexte, quel coup semble le plus important : jouer une reine ou un rocher ?
        Jouer une reine
     Y a-t-il des situations ou cette intuition s'inverse ?
        Si la valeur des rocks est plus grande que celle des queens et que la taille du board est limitée.

Q6 : Considérez l'ensemble des cases inaccessibles pour un joueur.
     Y a-t-il un espoir de récupérer certaines de ces cases (cad poser une reine) ?
        Oui,  bien-sûr en posant des rocks pour redébloquer des cases inaccessibles.

     Essayez de classifier les cases qui peuvent être récupérées et celles qui sont définitivenements perdues.
        Toutes les cases adjacentes à la reines ennemies même celle en diagonale.
        Les autres sont récupérables en posant un rocher.


#REPONSES AUX QUESTIONS DU TP4 :

Q1 : queenValue = 20 >> rockValue = 2

    sizeBoard : 3 && firstRock : false = 35 ms (0 s)
    sizeBoard : 5 && firstRock : false = 487 ms (0 s)
    sizeBoard : 7 && firstRock : false = 4916 ms (4 s)
    
    sizeBoard : 3 && firstRock : true = 4 ms (0 s)
    sizeBoard : 5 && firstRock : true = 234 ms (0 s)
    sizeBoard : 7 && firstRock : true = 3798 ms (3 s)
    
Q2 : queenValue = 3 >> rockValue = 1
    
    sizeBoard : 3 && firstRock : false = 40 ms (0 s)
    sizeBoard : 5 && firstRock : false = 601 ms (0 s)
    sizeBoard : 7 && firstRock : false = 5063 ms (5 s)
    
    sizeBoard : 3 && firstRock : true = 3 ms (0 s)
    sizeBoard : 5 && firstRock : true = 261 ms (0 s)
    sizeBoard : 7 && firstRock : true = 4065 ms (4 s)
    
Q3 : queenValue = 1 = rockValue = 1
    
    sizeBoard : 3 && firstRock : false = 32 ms (0 s)
    sizeBoard : 5 && firstRock : false = 794 ms (0 s)
    sizeBoard : 7 && firstRock : false = 8098 ms (8 s)
    
    sizeBoard : 3 && firstRock : true = 4 ms (0 s)
    sizeBoard : 5 && firstRock : true = 447 ms (0 s)
    sizeBoard : 7 && firstRock : true = 7157 ms (7 s)
    
Q4 : queenValue = 1 < rockValue = 3
    
    sizeBoard : 3 && firstRock : false = 29 ms (0 s)
    sizeBoard : 5 && firstRock : false = 728 ms (0 s)
    sizeBoard : 7 && firstRock : false = 6834 ms (6 s)
    
    sizeBoard : 3 && firstRock : true = 4 ms (0 s)
    sizeBoard : 5 && firstRock : true = 357 ms (0 s)
    sizeBoard : 7 && firstRock : true = 5857 ms (5 s)
    
Q5 : rockValue = 0
    
    sizeBoard : 3 && firstRock : false = 35 ms (0 s)
    sizeBoard : 5 && firstRock : false = 466 ms (0 s)
    sizeBoard : 7 && firstRock : false = 5121 ms (5 s)
    
    sizeBoard : 3 && firstRock : true = 5 ms (0 s)
    sizeBoard : 5 && firstRock : true = 279 ms (0 s)
    sizeBoard : 7 && firstRock : true = 3910 ms (3 s)