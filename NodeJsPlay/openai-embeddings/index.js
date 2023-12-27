const express = require("express");
const { Configuration, OpenAIApi } = require("azure-openai");

const app = express();
const port = 3000;

app.get("/", async (req, res) => {
  const text = "The food was delicious but the waiter was not good.";

  const configuration = new Configuration({
    azure: {
      apiKey: "ruby",
      endpoint: "https://aquamarine.openai.azure.com/",
    },
  });
  const openai = new OpenAIApi(configuration);
  const response = await openai.createEmbedding({
    model: "xmarket-text-embedding-ada-002",
    input: text,
  });

  const output = JSON.stringify(response?.data?.data?.[0]?.embedding);
  res.send(`<p>Input text: ${text}</p><p>Embedding: ${output}</p>`);
});

app.listen(port, () => {
  console.log(`OpenAI embedding app listening on port ${port}`);
});
