#!/usr/local/bin/python

if __name__ == '__main__':

	import bs4, requests
	import sys

	searchTerm = '+'.join(sys.argv[1:])
	#text = requests.get('https://www.youtube.com/results?search_query=new+animation+trailer').text
	text = requests.get('https://www.youtube.com/results?search_query='+searchTerm).text
	soup = bs4.BeautifulSoup(text)

	div = [ d for d in soup.find_all('div') if d.has_attr('class') and 'yt-lockup-dismissable' in d['class'] ]

	for d in div:
		img0 = d.find_all('img')[0]
		a0 = d.find_all('a')[0]
		imgL = img0['src'] if not img0.has_attr('data-thumb') else img0['data-thumb']
		a0 = [ x for x in d.find_all('a') if x.has_attr('title') ][0]
		print( (imgL, 'http://www.youtube.com/'+a0['href'], a0['title']) )
		
