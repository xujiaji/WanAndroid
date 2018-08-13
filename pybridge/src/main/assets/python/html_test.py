from html.parser import HTMLParser


class MyHTMLParser(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.resultValue = []

    def handle_starttag(self, tag, attrs):
        self.resultValue.append(tag)
        print('<%s>' % tag)
        print('attr:', attrs)
        for attr in attrs:
            if attr == ('class', 'list_navi listNavi'):
                print(True)
            print(attr)

    def handle_endtag(self, tag):
        print('</%s>' % tag)

    def handle_startendtag(self, tag, attrs):
        print('<%s/>' % tag)

    def handle_data(self, data):
        print(data)

    def handle_comment(self, data):
        print('<!--', data, '-->')

    def handle_entityref(self, name):
        print('&%s;' % name)

    def handle_charref(self, name):
        print('&#%s;' % name)

def runParser():
    parser = MyHTMLParser()
    parser.feed('''<html>
<head></head>
<body>
<ul class="list_navi listNavi">d11111</ul>
<!-- test html parser -->
    <p>Some <a href=\"#\">html</a> HTML&nbsp;tutorial...<br>END</p>
</body></html>''')
    return parser.resultValue

