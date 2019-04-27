#!/bin/bash
while true; do
	inotifywait classloadervortrag.tex
	sleep 0.5
	pdflatex -interaction=nonstopmode classloadervortrag.tex
done;
