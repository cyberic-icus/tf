fun main(){
    for(i in SomeService().SomeFun()) {
        println(i)
    }
    for(i in SomeService().SortFun()) {
        println(i)
    }
    for(i in SomeService().MapFun()) {
        println(i)
    }
    println(SomeService().CountFun())
}