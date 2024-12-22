import spacy
from spacy import displacy


class MySpacy:
    # Note: en_core_web_sm is too weak from my experience
    # en_core_web_trf is the best for general purpose, and it's smaller than _lg
    # GitHub: https://github.com/explosion/spacy-models
    def __init__(self, model="en_core_web_trf"):
        self.nlp = spacy.load(model)

    def get_doc(self, text):
        return self.nlp(text)

    # Note: **kwargs == *args with keywords
    def render_doc(self, doc, text=None, style="ent", **kwargs):
        if not doc:
            doc = self.get_doc(text)
        return displacy.render(doc, style, jupyter=False, **kwargs)
