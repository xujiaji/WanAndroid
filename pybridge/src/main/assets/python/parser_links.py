import json
from html.parser import HTMLParser


class Link(object):
    pass



class WanApiHTMLParser(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.startLink = False
        self.startParserLink = False
        self.result = []
        self.link = Link()

    def hasThisAttr(self, attrs, hopeAttr):
        for attr in attrs:
            if attr == hopeAttr:
                return True
        return False

    def handle_starttag(self, tag, attrs):
        if tag == 'div' and self.hasThisAttr(attrs, ('class', 'ulink_list')):
            self.startParserLink = True
        if tag == 'li' and self.startParserLink:
            self.startLink = True
        if tag == 'a' and self.startLink:
            self.link = Link()
            self.link.url = attrs[0][1]

    def handle_data(self, data):
        if self.startLink:
            self.link.title = data.strip()

    def handle_endtag(self, tag):
        if self.startParserLink and tag == 'ul':
            self.startParserLink = False
        if self.startLink and tag == 'li':
            self.startLink = False
            self.result.append(self.link)

    def handle_startendtag(self, tag, attrs):
        # print('<%s/>' % tag)
        if tag == 'img' and self.startLink:
            self.link.thumb = attrs[0][1]

    def handle_comment(self, data):
        # print('<!--', data, '-->')
        pass

    def handle_entityref(self, name):
        # print('&%s;' % name)
        pass

    def handle_charref(self, name):
        # print('&#%s;' % name)
        pass

def parserFriendLinks(htmlData):
    parser = WanApiHTMLParser()
    parser.feed(htmlData)
    return json.dumps(parser.result,  default=lambda obj: obj.__dict__, ensure_ascii=False)

# with request.urlopen('http://www.wanandroid.com/openapis') as f:
#     data = f.read()
#     print('Status:', f.status, f.reason)
#
#     for k, v in f.getheaders():
#         print('%s: %s' % (k, v))
#     # print('Data:', data.decode('utf-8'))
#     parser.feed(data.decode('utf-8'))
#     print(json.dumps(parser.result,  default=lambda obj: obj.__dict__, ensure_ascii=False))
