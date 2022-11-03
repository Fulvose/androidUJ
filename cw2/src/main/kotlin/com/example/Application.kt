package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

data class Product(val id: Int, val name: String, val price: Double, val country: String ){};

val listOfProducts = mutableListOf<Product>();

fun Route.display(){
    get("/products") {
        call.respond(allProducts());
    }
    get("/product-{id?}") {
        var id: String? = call.parameters["id"];
        var idNumber: Int = id!!.toInt();
        val productFound = product(idNumber)!!;
        call.respond(productFound);
    }
}

fun Application.productsRoutes()
{
    routing{
        display();
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }

object Products : Table() {
    val id = integer("id").autoIncrement() // Column<Int>
    val name = varchar("name", 50) // Column<String>
    val price = double("price")
    val country = varchar("country", 50) // Column<String>

    override val primaryKey = PrimaryKey(id, name = "PK_Products_ID")
}

private fun resultRowToProduct(row: ResultRow) = Product(
    id = row[Products.id],
    name = row[Products.name],
    price = row[Products.price],
    country = row[Products.country]
)

suspend fun allProducts(): List<Product> = dbQuery{
    Products.selectAll().map(::resultRowToProduct)
}

suspend fun product(id: Int): Product? = dbQuery {
    Products
        .select { Products.id eq id }
        .map(::resultRowToProduct)
        .singleOrNull()
}

suspend fun addNewProduct(name: String, price: Double, country: String): Product? = dbQuery {
    val insertStatement = Products.insert {
        it[Products.name] = name
        it[Products.price] = price
        it[Products.country] = country
    }
    insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProduct)
}


fun main() {

    listOfProducts.addAll(listOf(Product(1, "Stolik", 100.0, "Poland"),
        Product(2, "Olowek", 1.5, "Germany"),
        Product(3, "Mikrofon", 50.0, "China")));

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val database = Database.connect(
        "jdbc:sqlite:data.db",
        "org.sqlite.JDBC"
    )
    transaction(database) {
        SchemaUtils.create(Products)
    }
    runBlocking {
        if(allProducts().isEmpty()) {
            addNewProduct("Stolik", 100.0, "Poland")
            addNewProduct("Olowek", 1.5, "Germany")
            addNewProduct("Mikrofon", 50.0, "China")
        }
    }
    configureSerialization()
    configureRouting()
    productsRoutes()
}




