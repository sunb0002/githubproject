from flask import Flask, render_template
from markupsafe import escape

app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def route_basic():
    return {"output": "Hello World!"}


@app.route('/<name>/<int:age>')
def route_parameters(name, age):
    return {"output": f"Hello {escape(name)} is {age} years old!"}


@app.route('/pages')
@app.route('/pages/<name>')
def route_jinja2_template(name=None):
    return render_template('index.html', person=name)


@app.errorhandler(Exception)
def route_error(e):
    return f"Something is wrong! Details:<br/><br/>{e}", 503
