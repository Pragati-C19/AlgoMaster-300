import java.util.*;

public class MinDistance {
    
}


/*
 * Intuitions :
 
    1. We have given two words
    2. return the min number of operation required to convert word1 to word2
    3. Operation :
        - insert one charecter
        - delete one charecter
        - replace one charecter
 
 
 * Pattern :
 
    ^ Trace Example :

        word1 : horse ,  word2 : ros 

        - we can replace h with r word1 will be
            rorse

        - then will check o 
            after r there is o in word2 so skip

        - then check r
            in word2 there is s at third place so there we can do 2 things
            1. replace r with s then word1 will be rosse
            2. delete r from word1 then it will be rose

        - if we follow first operation 
            we need to do 2 more operations to remove s and e at end

        - if we follow second operation 
            we need to do 1 more operation to remove e

        - so we want minimum number of operations will take the second path
 

    Approach :

        1. apan word1 == word2 karnyasathi 3 main operations karu shakto nehmi
            - direct insert karat raha
                horse needs to convert to ros na 
                    - h and r are differet so -> insert r in word1 : rhorse
                    - h and o are differet so -> insert o in word1 : rohorse
                    - h and s are differet so -> insert s in word1 : roshorse

            - direct replace karat raha
                horse needs to convert to ros na 
                    - h and r are differet so -> replace h in word1 : rorse
                    - o and o are same so -> no need to replace o in word1 : rorse
                    - r and s are differet so -> replace r in word1 : rosse

            - delete tr apan fact end lach use karu shakto maybe.. to delete extra letters
                roshorse 
                    - delete h in word1 : rosorse
                    - delete o in word1 : rosrse
                    - delete r in word1 : rosse

                rosse
                    - delete s in word1 : rose
                    - delete e in word1 : ros

        2. so bagh doing 
            insertion + deletion gives -> 8 operations
            replaced + deletion gives -> 5 operations

            using insertion + replaced + deletion gives -> 3 operations
                - h and r are differet so   -> replace h in word1 : rorse
                - o and o are same so       -> no need to replace o in word1 : rorse
                - r and s are differet so   -> delete r in word1 : rose
                    but we have s next to r in word1 
                    so delete r
                - s and s are same so       -> no need to replace s in word1 : rose
                - at end we don't need e    -> delete e in word1 : ros

        3. Will use i for word1 and j for word2
            start for loops for both words
                for(i = 0 to m)
                    for(j = 0 to n)

            why ?
                                horse,  ros

                                i = 0, j = 0
                                    (h,r)
                     _________________|_________________
                    |                 |                 | 
                insert r      replace h with r       delete h 
                rhorse              rorse              orse

                (i=0, j=1)        (i=1, j=1)        (i=1, j=0) 
                  (h,o)             (o,o)             (o,r)

                 (i, j+1)         (i+1, j+1)         (i+1, j)


            kay hotay te nit samja ata..
                - apan donhi words ch letter by letter check karnar ahe right? so we need an index to check it
                - samaj apan index i ghetla to check horse and j ghetla to check ros
                - me index i vrchya letter vr 3 operations lavel 
                    insert lavla ki me j+1 karel ka?
                        karan me h chya agodr r lihun ghetlay ata mala word2 madhla next letter sathi shidhaychy
                    replace lavla ki me i+1 and j+1 karel ka?
                        karan me h la replace kartey r ni mhnje mala word2 cha ek fix letter bhetla..
                        ata mala next letter check karava lagel for next letter of word1
                        ata apan direct word1 replce thodi karat basnar ahe?
                        apan fact index pudhe pudhe gheu like mark as found kelya sarkh
                    delete lavla ki me i+1 karel
                        apan delete lavtoy karan me mhntey ohk h = r nahiye so me kay karte delete karte
                        till mala r = r bhetat nahi 
                        to bhetla ki me word2 cha next letter check karel

                - ata apan he 3 gosti check karnar tr pratek index sathi mala different counts bhettil
                - I need min so me kay karte yach tinhi cha min ghet jail
                    
                    minOfOperations = min ( dp[i][j+1], dp[i+1][j+1], dp[i+1][j])

                    dp[i][j] = 1 + minOfOperation


 
 * Pseudo Code :




 */