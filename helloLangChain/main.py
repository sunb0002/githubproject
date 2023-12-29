import asyncio
import os

import requests
import streamlit as st
from dotenv import find_dotenv, load_dotenv
from langchain.chains import LLMChain, SimpleSequentialChain
from langchain.chat_models import AzureChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.schema.output_parser import StrOutputParser
from transformers import pipeline

load_dotenv(find_dotenv())
HUGGINGFACE_API_TOKEN = os.getenv("HUGGINGFACE_API_TOKEN")
AZURE_OPENAI_ENDPOINT = os.getenv("AZURE_OPENAI_ENDPOINT")
AZURE_OPENAI_API_KEY = os.getenv("AZURE_OPENAI_API_KEY")
AZURE_OPENAI_DEPLOYMENT = os.getenv("AZURE_OPENAI_DEPLOYMENT") or "blah"
AZURE_OPENAI_API_VERSION = r"2023-05-15"
AUDIO_FILE_NAME = r"speech.wav"


# image to text
def image2text(img_url):
    # Use a pipeline as a high-level helper
    pipe = pipeline("image-to-text", model="Salesforce/blip-image-captioning-base")
    text = pipe(img_url)[0]["generated_text"]
    return text


# text to story by llm
def text2story():
    model0613 = AzureChatOpenAI(
        openai_api_base=AZURE_OPENAI_ENDPOINT,
        openai_api_version=AZURE_OPENAI_API_VERSION,
        azure_deployment=AZURE_OPENAI_DEPLOYMENT,
        openai_api_key=AZURE_OPENAI_API_KEY,
    )
    return LLMChain(
        llm=model0613,
        prompt=PromptTemplate.from_template(
            "Tell a story no longer than 50 words, based on this context: {context}"
        ),
        output_parser=StrOutputParser(),
    )


# story to audio
def text2audio(text):
    # Use a pipeline as a high-level helper
    API_URL = "https://api-inference.huggingface.co/models/facebook/mms-tts-eng"
    headers = {"Authorization": f"Bearer {HUGGINGFACE_API_TOKEN}"}
    audio_bytes = requests.post(API_URL, headers=headers, json={"inputs": text}).content
    with open(AUDIO_FILE_NAME, mode="wb") as f:
        f.write(audio_bytes)
    return


async def main():
    st.set_page_config(page_title="My LLM app")
    st.title("Image -> Text -> Audio")
    st.subheader("by LangChain + Azure OpenAI + HuggingFace (PyTorch-CPU)")
    uploaded_file = st.file_uploader("Choose a JPG/PNG image...", type=["jpg", "png"])

    if uploaded_file is not None:
        st.markdown("**Processing, please wait...**")
        st.snow()
        st.image(uploaded_file, caption="Uploaded image", use_column_width=True)
        main_chain = SimpleSequentialChain(chains=[text2story()], verbose=True)

        print("====START====")
        # Note: Input images must be placed under pre-defined folder for now.
        #       Streamlit is working on "file selector" feature which allows any paths.
        text = image2text(uploaded_file.name)
        with st.expander(f"Generated text with image: {uploaded_file.name}"):
            st.write(text)

        story = await main_chain.arun(text)
        with st.expander("Generated story with text"):
            st.write(story)

        with st.spinner("Generating audio with story..."):
            text2audio(story)
            st.audio(AUDIO_FILE_NAME)

        st.balloons()
        st.success("Completed!", icon="✅")
        print("====END====")


if __name__ == "__main__":
    asyncio.run(main())
