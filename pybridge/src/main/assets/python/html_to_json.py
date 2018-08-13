import json
from html.parser import HTMLParser


class Link(object):
    pass


class Title(object):
    pass


class WanApiHTMLParser(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.isTitle = False
        self.startParserLink = False
        self.isSubTitle = False
        self.result = []
        self.title = Title()
        self.link = Link()

    def hasThisAttr(self, attrs, hopeAttr):
        for attr in attrs:
            if attr == hopeAttr:
                return True
        return False

    def handle_starttag(self, tag, attrs):
        if tag == 'h3':
            self.isTitle = True
            self.title = Title()
            self.title.name = Title
            self.title.links = []
        elif tag == 'ul' and self.hasThisAttr(attrs, ('class', 'list_navi listNavi')):
            self.startParserLink = True
        elif self.startParserLink and tag == 'a':
            self.isSubTitle = True
            # print(attrs[0][1])
            self.link = Link()
            self.link.url = attrs[0][1]

    def handle_data(self, data):
        if self.isTitle:
            self.title.name = data
            # print('title: ', data)
            self.isTitle = False
        elif self.isSubTitle:
            # print('subTitle: ', data)
            self.isSubTitle = False
            self.link.name = data
            self.title.links.append(self.link)

    def handle_endtag(self, tag):
        # print('</%s>' % tag)
        if self.startParserLink and tag == 'ul':
            self.startParserLink = False
            self.result.append(self.title)

    def handle_startendtag(self, tag, attrs):
        # print('<%s/>' % tag)
        pass

    def handle_comment(self, data):
        # print('<!--', data, '-->')
        pass

    def handle_entityref(self, name):
        # print('&%s;' % name)
        pass

    def handle_charref(self, name):
        # print('&#%s;' % name)
        pass

def parserAPISPage(htmlData):
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
