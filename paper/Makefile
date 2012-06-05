paper.pdf: paper.tex paper.bib
	pdflatex paper
	bibtex paper
	pdflatex paper
	pdflatex paper

clean:
	rm -rf paper.log paper.toc paper.pdf paper.blg paper.bbl paper.aux
