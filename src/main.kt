fun main(){
    val catContest = Contest<Cat>()
    val petContest = Contest<Pet>()
    //val fishContest = Contest(Fish("Finny McGraw"))

    catContest.addScore(Cat("Fuzz Lightyear"),50)
    catContest.addScore(Cat("Katsu"),45)
    val topCat = catContest.getWinners().first().name
    println(topCat)
    petContest.addScore(Cat("Fuzz Lightyear"),50)
    petContest.addScore(Fish("Finny McGray"),56)
    val topAnimal = petContest.getWinners().first().name
    println(topAnimal)
}

abstract class Pet(var name:String)

class Cat(name:String):Pet(name)

class Dog(name:String):Pet(name)

class Fish(name:String):Pet(name)

class Contest<T:Pet>/*(t:T)*/{
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
        println("Sell Cat")
        return Dog("")
    }
}

class FishRetailer:Retailer<Fish>{
    override fun sell(): Fish {
        println("Sell Cat")
        return Fish("")
    }
}