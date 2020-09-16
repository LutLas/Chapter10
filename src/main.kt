fun main(){

    val catVet = Vet<Cat>()
    val fishVet = Vet<Fish>()
    val petVet = Vet<Pet>()

    catVet.treat(Cat("Garfeild"))
    petVet.treat(Cat("Katsu"))
    petVet.treat(Fish("Nsomba"))
    fishVet.treat(Fish("Kapenta"))

    val catContest = Contest(catVet)
    val petContest = Contest(petVet)
    val dogContest:Contest<Dog> = Contest(petVet)

    dogContest.vet.treat(Dog("Imbwa"))

    //val fishContest = Contest(Fish("Finny McGraw"))

    catContest.addScore(Cat("Fuzz Lightyear"),50)
    catContest.addScore(Cat("Katsu"),45)
    val topCat = catContest.getWinners().first().name
    println(topCat)
    petContest.addScore(Cat("Fuzz Lightyear"),50)
    petContest.addScore(Fish("Finny McGray"),56)
    val topAnimal = petContest.getWinners().first().name
    println(topAnimal)
    catContest.vet.treat(Cat("Boz"))

    val dogRetailer:Retailer<Dog> = DogRetailer()
    val catRetailer:Retailer<Cat> = CatRetailer()
    val petRetailer:Retailer<Pet> = CatRetailer()
    dogRetailer.sell()
}

abstract class Pet(var name:String)

class Cat(name:String):Pet(name)

class Dog(name:String):Pet(name)

class Fish(name:String):Pet(name)

class Contest<T:Pet>(var vet: Vet<in T>){
    private val scores: MutableMap<T,Int> = mutableMapOf()

    fun addScore(t:T, score: Int = 0){
        if (score >= 0) scores[t] = score
    }

    fun getWinners():MutableSet<T>{
        val highScore = scores.values.maxOrNull()
        val winners: MutableSet<T> = mutableSetOf()
        for ((t, score) in scores){
            if (score == highScore) winners.add(t)
        }
        return winners
    }
}

interface Retailer<out T>{
    fun sell():T
}

class CatRetailer:Retailer<Cat>{
    override fun sell(): Cat {
        println("Sell Cat")
        return Cat("")
    }
}

class DogRetailer:Retailer<Dog>{
    override fun sell(): Dog {
        println("Sell Dog")
        return Dog("")
    }
}

class FishRetailer:Retailer<Fish>{
    override fun sell(): Fish {
        println("Sell Fish")
        return Fish("")
    }
}

class Vet<T:Pet>{
    fun treat(t:T){
        println("Treating Pet ${t.name}")
    }
}