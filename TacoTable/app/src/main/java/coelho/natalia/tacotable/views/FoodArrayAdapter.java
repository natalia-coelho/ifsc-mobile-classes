package coelho.natalia.tacotable.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import coelho.natalia.tacotable.R;
import coelho.natalia.tacotable.models.Food;

public class FoodArrayAdapter extends ArrayAdapter<Food> {

    private LayoutInflater inflater;

    public FoodArrayAdapter(Context context, List<Food> foodList) {
        super(context, 0, foodList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.idTextView = convertView.findViewById(R.id.idTextView);
            viewHolder.categoryTextView = convertView.findViewById(R.id.categoriaTextView);
            viewHolder.nameTextView = convertView.findViewById(R.id.alimentoTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Food food = getItem(position);

        if (food != null) {
            viewHolder.idTextView.setText("Id: " + String.valueOf(food.getId()));
            viewHolder.categoryTextView.setText("Categoria: " + food.getCategory());
            viewHolder.nameTextView.setText("Alimento: " + food.getFoodName());
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView idTextView;
        TextView categoryTextView;
        TextView nameTextView;
    }
}
