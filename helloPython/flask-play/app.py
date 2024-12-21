from flask import Flask, render_template
from flask_socketio import SocketIO, emit, send
from markupsafe import escape

app = Flask(__name__)

app.config['SECRET_KEY'] = "to encrypt conversations"
socketio = SocketIO(app)
if __name__ == '__main__':
    socketio.run(app)

##### Flask General #####


@app.route('/', methods=['GET', 'POST'])
def route_basic():
    return {"output": "Hello World!"}


@app.route('/favicon.ico')
def ignore_favicon():
    return '', 204


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


##### WebSocket #####

@app.route('/ws')
def route_websocket():
    return render_template('ws.html', user="madoka")


@socketio.on('join')
def handle_my_custom_event(json):
    print(f'Someone joined us: {json}')
    # Send unnamed message
    send(f"You said: {json}", broadcast=False)
    # Emit named message
    emit('server event', f"Named event - you said: {json}")
    return
