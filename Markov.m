P = [33 27;
    48 12];
mc = dtmc(P);
mc.P
mc = dtmc(P,'StateNames',["Not Raining" "Raining"])
figure(2);
graphplot(mc,'ColorEdges',true);