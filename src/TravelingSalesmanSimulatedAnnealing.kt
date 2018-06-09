fun main(args: Array<String>) {
    val ts = TravelingSalesmanSimulatedAnnealing()

    ts.simulateAnnealing(30.0, 50, 0.9)
}

class TravelingSalesmanSimulatedAnnealing {
    private val travel = Travel(10)

    fun simulateAnnealing(startingTemperature: Double, numberOfIterations: Int, coolingRate: Double) {
        println("Starting SA with temperature: $startingTemperature, # of iterations: $numberOfIterations and colling rate: $coolingRate")
        var t = startingTemperature
        travel.generateInitialTravel()
        var bestDistance = travel.distance.toDouble()
        println("Initial distance of travel: $bestDistance")
        val bestSolution = travel

        for (i in 0 until numberOfIterations) {
            if (t > 0.1) {
                bestSolution.swapCities()
                val currentDistance = bestSolution.distance.toDouble()
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance
                } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                    bestSolution.revertSwap()
                }
                println("===============================================================")
                println("| ITERATION $i \n|")
                println("| Distance:  $currentDistance")
                println("| Temperature:  $t")
                t *= coolingRate
            } else {
                continue
            }
        }
        println("===================")
        println("| FINISHED ")
        println("| Best Distance: $bestDistance")
    }
}