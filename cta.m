%% Linear Regression of CTA Data

clear;

% Read all values
M = csvread('cta_colors.csv',0,0,[0,0,7,14])';

% Read values from 2001 to 2014
A = M(1:14,1:8);

% Get best fit line for each color line from 2001 to 2014
a = [];
b = [];
x = 2001:2014;
for i = 1:8
   fit = polyfit(x,A(:,i)',1);
   a = [a fit(1)];
   b = [b fit(2)];
end

% Calculates Predicted Value for 2015
c = [];
for i = 1:8
    u = a(i)*2015+b(i);
    c = [c u];
end

% Calculates Percent Error
B = M(15:15,1:8);
E = ((c-B)./c).*100;

C = {'Blue', 'Brown','Green','Orange','Pink', 'Purple', 'Red', 'Yellow'; 
    -5.4116    2.9765    5.6693    3.3117    3.6208    3.1948   -5.4189   11.7828};

Percent_Error = sprintf('%s \t %.2f \n',C{:})

% Example of Linear Regression
l = M(1:14,1:1);
m = a(1)*x+b(1);
plot(x,l,'*',x,m)
title('Blue Line Regression')
xlabel('Year')
ylabel('Number of Rides')

% Predicted 2016 Values
j = a*2016+b

