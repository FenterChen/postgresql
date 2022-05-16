package com.example.postgresql_server.services


//data class Beer(val id: Int, val name: String)
//
//@SpringBootApplication
//class KotlinJdbcApplication(@Autowired val jdbcTemplate: JdbcTemplate) : CommandLineRunner {
//    override fun run(vararg args: String?) {
//
//        //Create table ("IF NOT EXISTS" syntax may not be compatible with some databases):
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS favorite_beers(id INT, name VARCHAR(50))")
//
//        //Insert some records:
//        jdbcTemplate.execute("INSERT INTO favorite_beers(id, name) VALUES(1, 'Lagunitas IPA')")
//        jdbcTemplate.execute("INSERT INTO favorite_beers(id, name) VALUES(2, 'Jai Alai')")
//
//        //Declare rowmapper to map DB records to collection of Beer entities:
//        var rowMapper: RowMapper<Beer> = RowMapper<Beer> { resultSet: ResultSet, rowIndex: Int ->
//            Beer(resultSet.getInt("id"), resultSet.getString("name"))
//        }
//
//        //Query records to print out:
//        var results = jdbcTemplate.query("SELECT * FROM favorite_beers", rowMapper)
//
//        println("Original rows:")
//        results.forEach { rec -> println(rec) }
//
//        //Insert another record:
//        jdbcTemplate.execute("INSERT INTO favorite_beers(id, name) VALUES(3, 'Stella Artois')")
//
//        results = jdbcTemplate.query("SELECT * FROM favorite_beers", rowMapper)
//        println("After another insert:")
//        results.forEach { rec -> println(rec) }
//
//        //Delete record:
//        jdbcTemplate.execute("DELETE FROM favorite_beers WHERE ID = 1")
//
//        results = jdbcTemplate.query("SELECT * FROM favorite_beers", rowMapper)
//        println("After delete:")
//        results.forEach { rec -> println(rec) }
//    }
//}
