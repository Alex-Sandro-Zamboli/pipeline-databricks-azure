// Databricks notebook source
// MAGIC %md
// MAGIC Conferindo se os dados foram montados e temos acesso a pasta bronze

// COMMAND ----------

// MAGIC %python
// MAGIC dbutils.fs.ls("/mnt/dados/bronze")

// COMMAND ----------

// MAGIC %md
// MAGIC Lendo os dados na camada bronze

// COMMAND ----------

val path = "dbfs:/mnt/dados/bronze/dataset_imoveis/"
val df = spark.read.format("delta").load(path)

// COMMAND ----------

display(df)

// COMMAND ----------



// COMMAND ----------

// MAGIC %md
// MAGIC Transformando os campos json em colunas

// COMMAND ----------

display(df.select("anuncio.*"))

// COMMAND ----------

display(
  df.select("anuncio.*", "anuncio.endereco.*")
  )

// COMMAND ----------

val dados_detalahdos = df.select("anuncio.*", "anuncio.endereco.*")

// COMMAND ----------



// COMMAND ----------

// MAGIC %md
// MAGIC Removendo Coluna

// COMMAND ----------

val df_silver = dados_detalahdos.drop("caracteristicas", "endereco")
display(df_silver)

// COMMAND ----------

// MAGIC %md
// MAGIC Salvando na camada silver

// COMMAND ----------

val path = "dbfs:/mnt/dados/silver/dataset_imoveis"
df_silver.write.format("delta").mode("overwrite").save(path)


// COMMAND ----------


