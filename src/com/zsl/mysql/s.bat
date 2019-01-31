N=5000
dt=0.0015
F1=1-exp(-0.8*t)
F2=1-exp(-1.5*t)
Vc=[3,3]
m1=3
m2=3
Nt=9
Set Vr(1:Nt)=0
Set C=sum(Vc)
Set U=Survival signature
for n=1:N do
   for k=1:2 do
       for j=1:mk do
           M f(j,k)~Fk
       end for
       [Vt,Vi]=sort(M f)
       U0=1
       for m=1:C do
           Vc(Vi(m))=Vc(Vi(m))-1

           U1=U(Vc)
           q<-U1/U0
           if rand(1)<q then
              U0=U1
           else
             for all j:j*dt<Vi(m) do
               Vr(m)=Vr(m)+1
             end for
             break
           end if
       end for
       Vr=Vr/N
end for
pause;