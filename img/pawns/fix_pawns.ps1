$data = @('blue','red','green','yellow')
for ($i = 0; $i -le 3; $i += 1)
{
    for ($j = 1; $j -le 4; $j += 1) {
        Write-Host "pawn_$($data[$i])_$($j)"
        magick "pawn_$($data[$i])_$($j).png" "stroke_$($j).png" -gravity center -compose over -composite "pawn_$($data[$i])_$($j).png"
    }
}